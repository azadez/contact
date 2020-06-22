       Projet contact 

Liste des options 

Api WEB : 

Acc�s aux contacts uniquement en lecture sans se logguer

Pour se logguer le compte user est enregistr� dans la base de donn� et encrypt� avec BCryptPasswordEncoder
username : user
password :  test

base de donn�e persistante, si l'appli d�marre sans base ou avec une base  vide, 5 contacts/mail/adresses sont ins�r�s dans la base

foncionnalit� :   voir la liste des contacts
                  creer un contact
                  supprimer un contact (supprime ses emails mais pas ses adresses, la logique veut qu'on ne s'�change pas les emails)
                  modifier nom prenom du contact
                  lister les adresses et mails du contact
                  supprimer les items de cette liste
                   ajouter une adresse parmis les adresses ou ne vit pas deja le contact
                  ajouter un email parmis les emails non utilis�
                  supprimer un email/adresse d'un contact
                  ajouter 0, un ou des emails
                  ajouter 0, une ou des adresses
                  empeche la duplication d'email
                  
                  tous les chemins se trouvent via des boutons, logout possible


API xml, parametre possible : 
                action : (listContactsDetail/listContacts/getContact/delContact/updateContact)
                id 
                firstname
                lastname
                  
xml?action=listContactsDetail -> liste compl�te des contacts (avec adresses et mails)
xml?action=listContacts       -> liste avec uniquemeent les contacts sans mails et adresses
xml?action=getContact&id=x    -> liste les informations du contact x
xml?action=delContact&id=x    -> supprime le contact x avec ses emails et lib�re ses adresses
xml?action=updateContact&firstname=a&lastname=b -> update un contact, possible de n'update que l'un, l'autre ou les deux parametres

on pourrait rajouter des commandes pour lier contact adresse et contact email mais on le fait déjà en visuel par le navigateur

injection xml par postman

Pour se connecter � l'api avec postman entrer la requete :
                localhost:8080/login?username=user&password=test
possible d'injecter des contacts, adresses, emails un par un 

Sch�ma d'injection : 

localhost:8080/inject/customer

<customer>
<firstName>Valerian2</firstName>
<lastName>Lucien2</lastName>
</customer>

localhost:8080/inject/adresse

<adresse>
<adresse> Place des champs �lyss� 93000 Paris </adresse>
</adresse>

localhost:8080/inject/email

<email>
<email> valerian.lucien@laposte.net </email>
</email>