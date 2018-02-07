import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;

/**
 * Classe lanceur de l'application
 * @author LaPlume (http://laplume1870.blogspot.com/)
 */
public class Main extends JFrame implements Variables_Jeu, ActionListener {

    public JMenuBar barreMenu;
    public JMenu fichier;
    public JMenu options;
    public JMenuItem nouvellePartie;
    public JMenuItem quitter;
    public JMenu niveau, vitess_Balle, choix_versus;
    public ButtonGroup nivJeu, nivBalle, versus;
    public JRadioButton niv1, niv2, niv3, nivB1, nivB2, nivB3, vsIa, vsJoueur;
    public JOptionPane infoQuit, infoTerrain;

    Moteur_PingPong moteur;
    Table_PingPong table;

    /**
     * méthode pour initialiser tous les composants graphique de l'interface
     */
    private void initComposants(){

        barreMenu = new JMenuBar();
        fichier = new JMenu();
        options = new JMenu();
        choix_versus = new JMenu();
        nouvellePartie = new JMenuItem();
        quitter = new JMenuItem();
        niveau = new JMenu();
        vitess_Balle = new JMenu();
        nivJeu = new ButtonGroup();
        nivBalle = new ButtonGroup();
        versus = new ButtonGroup();
        vsIa = new JRadioButton();
        vsJoueur = new JRadioButton();
        niv1 = new JRadioButton();
        niv2 = new JRadioButton();
        niv3 = new JRadioButton();
        nivB1 = new JRadioButton();
        nivB2 = new JRadioButton();
        nivB3 = new JRadioButton();

        this.setTitle("Jeu de ping-pong");

        fichier.setText("Fichier");
        fichier.setMnemonic('F');
        nouvellePartie.setText("Nouvelle Partie");
        quitter.setText("Quitter");
        options.setText("Options");
        options.setMnemonic('O');
        niveau.setText("Niveau du jeu");
        vitess_Balle.setText("Vitesse de la balle");
        niv1.setText("Petite table");
        niv2.setText("Table normale");
        niv3.setText("Grande table");
        nivB1.setText("Rapide");
        nivB2.setText("Normale");
        nivB3.setText("Lente");
        vsIa.setText("Joueur vs IA");
        vsJoueur.setText("Joueur vs Joueur");
        choix_versus.setText("Mode");


        /* raccourci clavier pour l'option "Quitter" (ctrl + n) */
        nouvellePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));

        /* raccourci clavier pour l'option "Quitter" (ctrl + q) */
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));


        /* écouteurs d'évènements sur les options du menu */
        nouvellePartie.addActionListener(this);
        quitter.addActionListener(this);
        niv1.addActionListener(this);
        niv2.addActionListener(this);
        niv3.addActionListener(this);
        nivB1.addActionListener(this);
        nivB2.addActionListener(this);
        nivB3.addActionListener(this);
        vsIa.addActionListener(this);
        vsJoueur.addActionListener(this);


        /* ajout de l'option "Nouvelle Partie" et "Quitter" dans l'onglet "Fichier" */
        fichier.add(nouvellePartie);
        fichier.add(quitter);

        /* utilisation d'un objet ButtonGroup pour éviter la multi-sélection */
        nivJeu.add(niv1);
        nivJeu.add(niv2);
        nivJeu.add(niv3);

        nivBalle.add(nivB1);
        nivBalle.add(nivB2);
        nivBalle.add(nivB3);

        versus.add(vsIa);
        versus.add(vsJoueur);

        /* difficulté "Normal" et vitesse de la balle "normale" sélectionné par defaut */
        niv2.setSelected(true);
        nivB2.setSelected(true);
        vsIa.setSelected(true);

        /* ajout de l'option "Niveau de jeu" dans l'onglet "Options" */
        options.add(niveau);
        options.add(vitess_Balle);
        options.add(choix_versus);

        /* ajout des niveaux de difficultés au sous-menu "Niveau de jeu" */
        niveau.add(niv1);
        niveau.add(niv2);
        niveau.add(niv3);

        /* ajout des niveaux de vitesse de la balle au sous-menu "Vitesse de la balle" */
        vitess_Balle.add(nivB1);
        vitess_Balle.add(nivB2);
        vitess_Balle.add(nivB3);

        choix_versus.add(vsIa);
        choix_versus.add(vsJoueur);

        /* ajout de l'onglet "Fichier" et "Options" dans la barre de menu de l'interface */
        barreMenu.add(fichier);
        barreMenu.add(options);

        /* ajout de la barre de menu à l'interface */
        this.setJMenuBar(barreMenu);


    }


    public Main () {


        initComposants();


        /* évènements lors de la fermeture de l'application par le croix en haut à droite */
        this.addWindowListener(new WindowAdapter() {


            /**
            * Méthode appelée lors de la fermeture de l'application
            */
            @Override
            public void windowClosing(WindowEvent e) {

                /* Demande de confirmation à l'utilisateur pour quitter l'application */
                infoQuit = new JOptionPane();
                @SuppressWarnings("static-access")
                int choix = infoQuit.showConfirmDialog(null, "Voulez-vous vraiment quitter le jeu ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (choix == JOptionPane.YES_OPTION) {

                   System.exit(0);
                }
                else {

                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }

        });

         table = new Table_PingPong();
         table.ajoutInterface(this.getContentPane());
         moteur = new Moteur_PingPong(table);


         this.pack();
         this.setBounds(0, 0, table.larg_Table + 90 , table.hauteur_Table + 80);

         /* Place au milieu de l'écran la fenêtre d'application */
         this.setLocationRelativeTo(null);

         this.setVisible(true);
    }


    /**
     * méthode gérant les évènements sur les éléments graphiques
     * @param e l'élément en question
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nouvellePartie) {

            moteur.nouvellePartie();
        }


        if (e.getSource() == quitter) {

            moteur.finJeu();

        }


        /* sélection de la vitesse de la balle */

        /* balle rapide */
        if (e.getSource() == nivB1) {

            table.vitesse_JEU = 10;
        }

        /* balle normale */
        if (e.getSource() == nivB2) {

            table.vitesse_JEU = 15;
        }

        /* balle lente */
        if (e.getSource() == nivB3) {

            table.vitesse_JEU = 25;
        }


        /* sélection de la petite table */
        if (e.getSource() == niv1) {

            /* Demande de confirmation à l'utilisateur pour quitter l'application */
            infoTerrain = new JOptionPane();
            @SuppressWarnings("static-access")
            int choix = infoTerrain.showConfirmDialog(null, "Le changement de table redémarrera une nouvelle partie voulez-vous vraiment changer de table ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choix == JOptionPane.YES_OPTION) {

                /* modification des dimensions de la table */
                table.larg_Table = 600;
                table.hauteur_Table = 400;
                table.bas_Table = table.hauteur_Table;

                /* modification du traçage des lignes de la table */
                table.ligne_Mediane = table.hauteur_Table;
                table.x1 = table.larg_Table/2;
                table.x2 = table.larg_Table/2;

                /* placement de la raquette */
                table.place_Raquette = table.larg_Table - 50;
                table.long_Raquette = 50;

                /* zone de déplacement de la balle */
                table.balle_x_max = table.larg_Table - INCR_BALLE;
                table.balle_y_max = table.hauteur_Table - INCR_BALLE;

                /* redimensionnement de la fenêtre */
                this.setBounds(0, 0, table.larg_Table + 90 , table.hauteur_Table + 80);
                this.setLocationRelativeTo(null);


                /* démarrage d'une nouvelle partie en simulant un appui sur la touche N */
                try {

                    Robot simulateur = new Robot();
                    simulateur.setAutoDelay(100);
                    simulateur.keyPress(KeyEvent.VK_N);

                }catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                table.repaint();
                table.revalidate();
            }

            else if (table.larg_Line == 300) {

                niv2.setSelected(true);
            }
            else {

                niv3.setSelected(true);
            }

        }

        /* sélection de la table normale (par defaut) */
        if (e.getSource() == niv2) {

            /* Demande de confirmation à l'utilisateur pour quitter l'application */
            infoTerrain = new JOptionPane();
            @SuppressWarnings("static-access")
            int choix = infoTerrain.showConfirmDialog(null, "Le changement de table redémarrera une nouvelle partie voulez-vous vraiment changer de table ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choix == JOptionPane.YES_OPTION) {

                /* modification des dimensions de la table */
                table.larg_Table = LARGEUR_TABLE;
                table.hauteur_Table = HAUTEUR_TABLE;
                table.bas_Table = BAS_TABLE;

                /* modification du traçage des lignes de la table */
                table.ligne_Mediane = table.hauteur_Table;
                table.x1 = table.larg_Table/2;
                table.x2 = table.larg_Table/2;

                /* placement de la raquette */
                table.place_Raquette = table.larg_Table - 50;
                table.long_Raquette = 120;

                /* zone de déplacement de la balle */
                table.balle_x_max = table.larg_Table - INCR_BALLE;
                table.balle_y_max = table.hauteur_Table - INCR_BALLE;

                /* redimensionnement de la fenêtre */
                this.setBounds(0, 0, table.larg_Table + 90 , table.hauteur_Table + 80);
                this.setLocationRelativeTo(null);


                /* démarrage d'une nouvelle partie en simulant un appui sur la touche N */
                try {

                    Robot simulateur = new Robot();
                    simulateur.setAutoDelay(100);
                    simulateur.keyPress(KeyEvent.VK_N);

                }catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                table.repaint();
                table.revalidate();

            }

            else if (table.larg_Line == 220) {

                niv1.setSelected(true);
            }
            else {

                niv3.setSelected(true);
            }

        }

        /* sélection de la grande table */
        if (e.getSource() == niv3) {

            /* Demande de confirmation à l'utilisateur pour quitter l'application */
            infoTerrain = new JOptionPane();
            @SuppressWarnings("static-access")
            int choix = infoTerrain.showConfirmDialog(null, "Le changement de table redémarrera une nouvelle partie voulez-vous vraiment changer de table ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choix == JOptionPane.YES_OPTION) {

                /* modification des dimensions de la table */
                table.larg_Table = 2500;
                table.hauteur_Table = 1600;
                table.bas_Table = 1600;

                /* modification du traçage des lignes de la table */
                table.ligne_Mediane = table.hauteur_Table;
                table.x1 = table.larg_Table/2;
                table.x2 = table.larg_Table/2;

                /* placement de la raquette */
                table.place_Raquette = table.larg_Table - 50;
                table.long_Raquette = 200;

                /* zone de déplacement de la balle */
                table.balle_x_max = table.larg_Table - INCR_BALLE;
                table.balle_y_max = table.hauteur_Table - INCR_BALLE;


                /* redimensionnement de la fenêtre */
                this.setBounds(0, 0, table.larg_Table + 90 , table.hauteur_Table + 80);
                this.setLocationRelativeTo(null);


                /* démarrage d'une nouvelle partie en simulant un appui sur la touche N */
                try {

                    Robot simulateur = new Robot();
                    simulateur.setAutoDelay(100);
                    simulateur.keyPress(KeyEvent.VK_N);

                }catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                table.repaint();
                table.revalidate();

            }

            else if (table.larg_Line == 220) {

                niv1.setSelected(true);
            }
            else {

                niv2.setSelected(true);
            }

        }
        if (e.getSource() == vsIa) {
          table.solo = true;
        }

        if (e.getSource() == vsJoueur) {
          table.solo = false;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
