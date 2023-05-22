<!-- Ajoutez un script JavaScript pour valider le formulaire et afficher les messages d'erreur dans une boîte de dialogue d'alerte -->
function validateForm() {
        erreur = true;
        err = "";
        var nom = document.getElementsByName("nomClub")[0].value;
        var object = document.getElementsByName("ObjectClub")[0].value;
        var prix = document.getElementsByName("prixClub")[0].value;
    var dateInput = document.getElementsByName("dateCreation")[0].value;
        if (nom == "" || object == ""|| prix == ""|| dateInput == "") {
            err += "Veuillez remplir tous les champs obligatoires.\n";
            erreur=false;
        }

            if (nom.length > 30 || nom.length < 2) {
                err += " la longeur  du  nom du club doit etre entre 2 et 30 !\n";
                erreur = false;
            }

                if (object.length > 30 || object.length < 2) {
                    err += " la longeur  de l'objectif du club doit etre entre 2 et 30 !\n";
                    erreur = false;
                }

                    if (prix< 10.0 || prix > 100.0) {
                        err += "le prix doit étre entre 10 et 100 D\n";
                        erreur = false;
                    }
// Vérification de la date
    var today = new Date();
    var dateChoisie = new Date(dateInput);

    if (dateChoisie > today) {
        err += "La date doit être inferieur ou egale à la date d'aujourd'hui !\n";
        erreur = false;
    }

    if (erreur == false) {
        alert(err);
        return false;
    } else {
        // Ajout dans la base de données
        if (confirm("Voulez-vous ajouter ces données dans la base de données ?")) {
            // Le code pour ajouter les données dans la base de données
            alert("Les données ont été ajoutées avec succès !");
        } else {
            return false;
        }
    }
    }