# backend

Pentru a introduce datele sub forma de CSV obtinute prin manipularea CSV-ului de la laborator cu libraria spotipy din Python, am creat un controller numit **DataController** si un endpoint **/api/data/csv/tracks** care permite POST cu acel continut. 
Acest continut este primit si parsat cu un **csvParser** din pachetul **utils**. Datele primite se salveaza intr-un fisier si folosind un CSVReader citesc rand cu rand datele si obtin o lista de String[]. Impart acea lista in 4 mini-liste si salvez informatiile in baza de date folosind 4 **threaduri**, cate una pentru fiecare mini-lista. Scopul a fost pentru a micsora timpul de asteptare pentru salvarea datelor.
Tot in pachetul utils, clasa **DataSaving** se ocupa cu salvarea informatiilor in baza de date. Metoda **saveTracks** primeste o astfel de mini-lista si salveaza fiecare track (melodie), artist, album si gen in baza de date folosind **tranzactii**, pentru a asigura ca datele de care depinde un obiect sunt deja salvate (de ex, trebuie mai intai salvat artistul si apoi melodia, deoarece in clasa Track exista o referinta catre artistul care o canta).

Pentru register si login requesturile de la client (React) se fac in **AuthController** ce prezinta un api cu doua endpointuri - **/login** si **/register**. Acestea primesc datele si manipularea acestora este transmisa catre business logic layer, in **AuthService**. Pentru login, in AuthService verificam ca userul sa fie existent, iar pentru register verificam sa nu fie deja existent si daca este asa, il salvam folosind Serviciul **UsersService** care comunica cu data layer-ul **UsersRepository** si obtine de la baza de date daca exista sau nu userul folosind metoda **findByEmail**. 
 
Pentru a afisa toate melodiile / artistii / albumele sau albumele cu un artist specific sau melodiile dintr-un album, pentru a cauta muzica, pentru a se verifica daca o melodie este favorita sau nu, endpointurile care permit aceste operatii sunt in **MusicController** care preia requesturile si transmite mai departe cerei catre **MusicService**, in functie de caz o metoda diferite din serviciu impreuna cu parametrii necesari.
In MusicService sunt metoda care returneaza informatiile cerute de client apeland la data layerele **UserRepository**, **TracksRepository**, **AlbumsRepository** si **GenreRepository**. In aceste data layere am folosit fie query-uri catre baza de date scrise manual cu **JPQL** sau folosind conventiile de nume din Spring de tipul *findBy...*.

Pentru interactiunea dintre user si datele specifice pentru el din baza de date, am folosit un controller **UserCustomProfileController** care se ocupa cu requesturi de *add-favourite-x*, *remove-favourite-x*, *add-recent-x*, *x-recommendations/{id}* care apeleaza la serviciul **MusicInfoForUserService** care preia date din data layer sau pune date in baza de date prin apel la data layer.

Pentru diverse informatii despre user, gasirea in functie de email / id, schimbarea username-ul, endpointurile folosite de client se gasesc in **UsersController** care, la fel, apeleaza logica din **UsersService** si UsersService prin repositories.

Am tratat cateva exceptii create de mine pentru diverse evenimente cu nume sugestive in pachetul de **exceptions**, de exemplu pentru register / login am folosit **AlreadyExistentUser** si **NonexistentUser**.

In cazul POST requesturilor de la client si primirea obiectelor am folosit DTO in pachetul **dto** si de pe urma acestora am construit obiectele pe baza modelelor din pachetul **models**. Aceasta impartire a fost facuta, deoarece de la client nu se primesc mereu modele complete, ci doar parti. 

Structura modelelor (care sunt asociate cu un Entity din baza de date pe baza ORM):


![image](https://github.com/Proiect-PA/backend/assets/83142073/5075c90c-216b-443f-bcb8-769d10a61fae)
![image](https://github.com/Proiect-PA/backend/assets/83142073/12c7a729-1efe-4ac8-aaa6-185fecac9069)
![image](https://github.com/Proiect-PA/backend/assets/83142073/6d074454-0fd6-433b-aa5d-0d4e3b4028b5)



**API-ul**

![image](https://github.com/Proiect-PA/backend/assets/83142073/4491a52a-8c31-4a55-9111-2866fa72c865)
![image](https://github.com/Proiect-PA/backend/assets/83142073/a51620d3-6a36-4705-b620-29ee3150c53d)
![image](https://github.com/Proiect-PA/backend/assets/83142073/147984b6-6be1-4b48-a5ae-588f822e0e03)


