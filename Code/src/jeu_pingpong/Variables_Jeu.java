/**
 * Interface regroupant toutes les variables du jeu (dimension table, raquette...)
 * @author LaPlume (http://laplume1870.blogspot.com/)
 */
public interface Variables_Jeu {

    /* toutes les dimensions sont exprimées en pixels */

    /* dimension de la table */
    public final int LARGEUR_TABLE = 1200;
    public final int HAUTEUR_TABLE = 800;
    public final int HAUT_TABLE = 0;
    public final int BAS_TABLE = 800;

    /* définie la position de départ des raquettes ainsi que leurs dimension */
    public final int RAQUETTE_Y = HAUTEUR_TABLE/2;
    public final int RAQUETTE_X = LARGEUR_TABLE - 50;
    public final int RAQUETTE_ORDI_Y = HAUTEUR_TABLE/2;
    public final int RAQUETTE_ORDI_X = 20;
    public final int LONGUEUR_RAQUETTE = 80;
    public final int LARGEUR_RAQUETTE = 8;

    /* rapidité de mouvement de la raquette (en pixels) */
    public final int INCR_RAQUETTE = 8;

    /* rapidité de mouvement de la balle (en pixels) */
    public final int INCR_BALLE = 10;

    /* définition de l'espace de déplacement de la balle */
    public final int BALLE_Y_MIN = 1 + INCR_BALLE;
    public final int BALLE_X_MIN = 1 + INCR_BALLE;
    public final int BALLE_Y_MAX = HAUTEUR_TABLE - INCR_BALLE;
    public final int BALLE_X_MAX = LARGEUR_TABLE - INCR_BALLE;

    /* définition de la position de départ de la balle au lancement d'une partie */
    public final int BALLE_Y_DEPART = HAUTEUR_TABLE / 2 - INCR_BALLE / 2;
    public final int BALLE_X_DEPART = LARGEUR_TABLE / 2 - INCR_BALLE / 2;

    public final int SCORE_GAGNANT = 5;

    /* pour ralentir le jeu en cas d'ordinateur très rapide (millisecondes) */
    public final int RALENTIE_JEU = 10;

    public final boolean SOLO = true;

}
