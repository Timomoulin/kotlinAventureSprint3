import item.*
import jeu.Jeu
import jeu.Sort
import jeu.TirageDes
import personnage.Personnage

// instanciation des Sorts (pour le(s) mage(s))
val sortDeFeu = Sort("Boule de Feu") { caster, cible ->
    run {
        // Tire un nombre de dés qui depend de l'attaque du caster (mage)
        val tirageDes = TirageDes(caster.attaque / 3, 6)
        var degat = tirageDes.lance()
        degat = maxOf(1, degat - cible.calculeDefense())
        cible.pointDeVie -= degat
        println("Le sort de boule de feu inflige $degat à ${cible.nom}")
    }
}
val sortDeGuerison = Sort("Sort de Guérison") { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 10)
        // on ajoute un bonus au soin qui corespond au bonus d'attaque/2
        val soin = tirageDes.lance() + caster.attaque/2
        cible.pointDeVie += soin
        println("Le sort de guérison soigne ${soin} PV à ${cible.nom}")
    }
}
val sortProjectileMagique = Sort("Sort de projectile magique", { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 6)
        //On lance plusieurs fois projectile magique cela depend du score d'attaque
        repeat(caster.attaque / 2) {
            var degat = tirageDes.lance()
            degat = maxOf(1, degat - cible.calculeDefense())
            cible.pointDeVie -= degat
            println("Le projectile magique inflige $degat à ${cible.nom}")
        }
    }
})

val projectionAcide = Sort("Sort de projection acide", { caster, cible ->
    run {
        val tirageDes = TirageDes(1, 10)
            var degat = tirageDes.lance()
            degat = maxOf(1, degat - cible.calculeDefense())
            cible.pointDeVie -= degat
            println("Le jet d'acide inflige $degat à ${cible.nom}")

    }
})

//instanciation des types d'armes
val typeEpeeLongue = TypeArme("Epee longue", 1, 8, 2,20)

val typeEpeeCourte = TypeArme("Epee courte", 1, 6, 2,18)
val typeDague = TypeArme("Epee courte", 1, 4, 3,15)
val typeLance = TypeArme("Lance", 1, 6, 3,18)
val typeMarteau = TypeArme("Marteau", 1, 8, 2,20)

//instanciation des types d'armures
val typeArmureCuir = TypeArmure("Armure en cuir", 1)

//instanciation des qualités des objets
val qualiteCommun = Qualite("commun", 0, "\u001B[32m")
val qualiteRare = Qualite("rare", 1, couleur = "\u001B[34m")
val qualiteEpic = Qualite("epic", 2, "\u001B[35m")
val qualiteLegendaire = Qualite("legendaire", 3, "\u001B[33m")


fun main() {
    //instanciation des armes des monstres
    val epee = Arme("Épée Courte", "Une épée courte tranchante", typeEpeeCourte, qualiteCommun)
    val lance = Arme("Lance", "Une lance pointue", typeLance, qualiteRare)
    val dague = Arme("Dague", "Une dague extrêmement pointue", typeDague, qualiteEpic)
    val marteau = Arme("Marteau", "un marteau legendaire pourfendeur de troll", typeMarteau, qualiteLegendaire)
    // instanciation des potions et bombes des monstres
    val potionDeSoin1 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin2 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin3 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin4 = Potion("Grande Potion de Soin", "Restaure les points de vie", 30)

    // Instanciation des Monstres
    val kobold =
        Personnage("Marvin le Kobold", 25, 25, 3, 4, 6, 12, dague, inventaire = mutableListOf(potionDeSoin2, dague))
    val gobelin = Personnage(
        "Antoine le gobelin",
        pointDeVie = 20,
        pointDeVieMax = 20,
        attaque = 5,
        defense = 4,
        vitesse = 11,
        endurance = 6,
        armeEquipee = epee,
        inventaire = mutableListOf(potionDeSoin3, epee)
    )
    val troll = Personnage(
        "Nassim le troll",
        pointDeVie = 100,
        100,
        6,
        3,
        12,
        3,
        marteau,
        inventaire = mutableListOf(potionDeSoin4, marteau)
    )

    val jeu = Jeu(listOf(kobold, gobelin, troll))

    jeu.lancerCombat()

}