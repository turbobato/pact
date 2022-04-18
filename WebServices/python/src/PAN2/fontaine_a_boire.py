import requests

# script basique permettant de rechercher une fontaine en fonction d'un lieu et renvoyer sa position géographique et sa disponibilité

q=input("Entrez votre requête :\n")

rows=input("Entrez le nombre de résultats souhaités\n")

request = "http://opendata.paris.fr/api/records/1.0/search/?dataset=fontaines-a-boire&q="+q+"&rows="+rows+"&facet=type_objet&facet=modele&facet=commune&facet=dispo"

response = requests.get(request)


if "records" not in response.json() :
    print("Erreur, voici la réponse de la reqûete :\n")
    print(response.json())

else :
    results = response.json()["records"]

    if (results == []):
        print("Aucune réponse ne satisfaisant votre reqûete n'a été trouvée")

    else :
        print("Voici vos résultats")
        j=1
        for i in results :
            print(str(j)+"\n")
            j+=1
            fields=i["fields"]
            print("Emplacement de la fontaine :\n")
            lieu = fields["voie"] + " à " + fields["commune"]
            print(lieu+"\n")
            dispo = fields["dispo"]
            if dispo=="OUI":
                print("La fontaine est disponible \n")
            else : 
                print("La fontaine n'est pas disponible jusqu'à "+fields["fin_ind"]+" pour raison de "+fields["motif_ind"]+"\n")
            print("Fontaine de type : " + fields["type_objet"]+ "\n")