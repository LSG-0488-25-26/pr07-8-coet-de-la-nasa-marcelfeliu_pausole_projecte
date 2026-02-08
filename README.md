# COET DE LA NASA
- Per aquesta aplicació, hem agafat la api de _Rick And Morty_
- Tenim 2 vistes principals, amb diferents sistemes:
    + Navegació amb _Routes_
    + Arquitectura MVVM amb _LiveData_
    + Integració d'API amb _Retrofit_
    + Base de dades interna amb _Room_
    + Un cercador per buscar elements en una _Lazy column_
    + Una _Topbar_ i _BottomBar_ amb _Scaffold_
    + Adaptative i responsive
---

## CharacterList View
- Aquí és on tenim la LazyColumn amb els personatges
- Cada element de la llista té informació del personatge
- Es pot veure tant la TopBar com la BottomBar
- Podem picar sobre "Captured" per veure els persotages capturats
<img height="520" alt="image" src="https://github.com/user-attachments/assets/3aa84f66-1f40-47b9-8b35-e18d8c1cc969" />

## CharacterDetails View
- Aquí és on tenim tota la informació del personatge seleccionat
- Hi ha un botó per tornar a la llista
- Es guarda la posició del scroll de la llista al tornar enrere
- Pots capturar o alliberar al personatge, depenent de si estaba capturat abans o no
    - Aquesta informació es guarda a la base de dades 
<img height="520" alt="image" src="https://github.com/user-attachments/assets/3981680d-142b-4c24-87ef-487bcb964906" />
    
- Quan un personatge es marcat com a capturat, la imatge de perfil se li torna en blanc i negre.
<img height="520" alt="image" src="https://github.com/user-attachments/assets/c56a9ce6-37ab-48d6-8297-3ff98f6d9327" />

## Top Bar
- En el top bar tenim un icono d'una lupa que serveix per obrir el cercador i poder filtrar personatges pel seu nom. Quan fem clic al botó apareix el "search bar" i quan el tornem a apretar s'amaga.
<img height="520" alt="image" src="https://github.com/user-attachments/assets/47ea86e0-1464-4e83-b98f-3831d9cda23f" />


## Bottom Bar
En la part d'assota de la aplicacó hi han dos botóns:
- Home: Torna a la llista de personatges generals
- Captured: Mostra una llista amb els personatges capturats
<img height="520" alt="image" src="https://github.com/user-attachments/assets/e08e96dc-8a1d-46a8-9be3-161502216ba3" />

