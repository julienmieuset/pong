import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Cette classe se charge de dessiner la table de ping-pong
 * et afficher les coordonnées de l'endroit où l'utilisateur a cliqué
 * @author LaPlume (http://laplume1870.blogspot.com/)
 */
public class Table_PingPong extends JPanel implements Variables_Jeu {

    public JLabel label;

    public int larg_Table = LARGEUR_TABLE;
    public int hauteur_Table = HAUTEUR_TABLE;
    public int larg_Line = 300;
    public int long_Ligne = 200;
    public int x1 = larg_Table/2;
    public int x2 = larg_Table/2;
    public int ligne_Mediane = hauteur_Table;
    public int place_Raquette = RAQUETTE_X - LONGUEUR_RAQUETTE/2;
    public int bas_Table = BAS_TABLE;
    //public Point point = new Point(0, 0);

    public int balle_x_max = BALLE_X_MAX;
    public int balle_y_max = BALLE_Y_MAX;

    public int vitesse_JEU = RALENTIE_JEU;

    public int long_Raquette = LONGUEUR_RAQUETTE;
    public int raquetteOrdi_Y = RAQUETTE_ORDI_Y;
    private int raquetteJoueur_Y = RAQUETTE_Y;

    private int balle_X = BALLE_X_DEPART;
    private int balle_Y = BALLE_Y_DEPART;

    Dimension tailleTable = new Dimension(LARGEUR_TABLE, HAUTEUR_TABLE);


    public Table_PingPong () {

        Moteur_PingPong moteur = new Moteur_PingPong(this);

        /* écouteur pour récupérer les coordonnées des clics de la souris */
        //addMouseListener(moteur);

        /* écouteur pour gérer les mouvements de la raquette par le déplacement de la souris */
        addMouseMotionListener(moteur);

        /* écouteur pour récupérer les commandes du clavier (n, s, et q) */
        addKeyListener(moteur);

    }


    public void ajoutInterface (Container conteneur) {

        /* constitution de l'interface de jeu (table, affichage coordonnées) */
        conteneur.setLayout(new BoxLayout(conteneur, BoxLayout.Y_AXIS));
        conteneur.add(this);

        /* création du JLabel pour l'affichage de la position de la souris sur l'interface de jeu */
        label = new JLabel("n : nouvelle partie, s : servir, q : quitter (accès par le menu également)");
        conteneur.add(label);
    }


    /**
     * Méthode affectant la taille de la table au cadre de jeu
     * @return la taille de la table de jeu
     */
    public Dimension getPreferredSize() {

        return tailleTable;
    }


   /**
    * cette méthode dessine et mmets en place tous les éléments graphique à déssiner
    * (raquettes, balle...)
    * Elle est appelée à chaque rafraichissement de l'affichage
    * @param Graphisme conteneur de tous les éléments
    */
    public void paintComponent (Graphics Graphisme) {

        super.paintComponent(Graphisme);

        /* la table en forme de rectangle */
        Graphisme.setColor(Color.BLACK);
        Graphisme.fillRect(0, 0, larg_Table, hauteur_Table);

        /* la 1 ère raquette en forme de rectangle */
        Graphisme.setColor(Color.RED);
        Graphisme.fillRect(place_Raquette, raquetteJoueur_Y, LARGEUR_RAQUETTE, long_Raquette);

        /* la 2 ème raquette en forme de rectangle */
        Graphisme.setColor(Color.BLUE);
        Graphisme.fillRect(RAQUETTE_ORDI_X, raquetteOrdi_Y, LARGEUR_RAQUETTE, long_Raquette);

        /* la balle en forme de cercle */
        Graphisme.setColor(Color.WHITE);
        Graphisme.fillOval(balle_X, balle_Y, 10, 10);

        /* les lignes de la table */
        Graphisme.setColor(Color.WHITE);
        Graphisme.drawLine(x1, 10, x2, ligne_Mediane);

        /* attribue le focus à la table pour que l'écouteur des touches (n, s et q)
           envoie les commandes à la table */
        this.requestFocus();
    }


    /**
     * méthode permettant de connaitre la position de la raquette sur la table de jeu
     * @return la position de la raquette du joueur
     */
    public int positionRaquetteJoueur () {

        return raquetteJoueur_Y;
    }


    /**
     * méthode permettant de déplacer la raquette du joueur
     * @param deplacement indicateur de déplacement
     */
    public void mouvementRaquetteJoueur (int deplacement) {

        this.raquetteJoueur_Y = deplacement;
        this.repaint();
    }


    /**
     * méthode permettant de déplacer la raquette de l'ordinateur
     * @param deplacement indicateur de déplacement
     */
    public void mouvementRaquetteOrdi (int deplacement) {

        this.raquetteOrdi_Y = deplacement;
        this.repaint();
    }


    /**
     * méthode gérant les messages du jeu
     * @param mess message à afficher
     */
    public void messagesJeu (String mess) {

        label.setText(mess);
        this.repaint();
    }

    /**
     * méthode définissant la position de la balle
     * @param x abcisse de la balle
     * @param y ordonnée de la balle
     */
    public void positionBalle (int x, int y) {

        balle_X = x;
        balle_Y = y;
        this.repaint();
    }



}
