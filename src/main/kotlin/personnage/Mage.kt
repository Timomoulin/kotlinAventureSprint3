package personnage

import jeu.Sort

class Mage(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    val grimoire: MutableList<Sort> = mutableListOf()
) : Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse) {

    // Méthode pour afficher les sorts du grimoire
    fun afficherGrimoire() {
        println("Grimoire de $nom:")
        if (grimoire.isEmpty()) {
            println("Le grimoire est vide.")
        } else {
            for ((index, sort) in grimoire.withIndex()) {
                println("$index. ${sort.nom}")
            }
        }
    }

    // Méthode pour choisir et lancer un sort du grimoire
    fun choisirEtLancerSort(cible: Personnage) {
        if (grimoire.isEmpty()) {
            println("Le grimoire est vide. Vous ne pouvez pas lancer de sort.")
            return
        }

        // Afficher les sorts du grimoire
        afficherGrimoire()

        // Demander au joueur de choisir un sort à lancer
        print("Choisissez un sort à lancer (entrez le numéro) : ")
        val choixSort = readLine()?.toIntOrNull()

        if (choixSort != null && choixSort >= 0 && choixSort < grimoire.size) {
            val sortChoisi = grimoire[choixSort]
            // Appel de la fonction effet du sort sur la cible
            sortChoisi.effet(cible)
            println("$nom lance le sort ${sortChoisi.nom} sur ${cible.nom}.")
        } else {
            println("Choix invalide.")
        }
    }


}