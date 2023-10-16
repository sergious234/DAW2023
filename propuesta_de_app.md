#  **PROPUESTA DE TRABAJO**

<br>

# SPANISH RANKING

* ## Idea Principal

    Una aplicación dedicada al seguimiento de los clanes españoles 
    durante las campañas en World of Tanks.


---

* ## Motivación 

    World of Tanks es un juego de tanques multijugador en el que en un
    periodo de tiempo los clanes pueden disputar batallas para controlar 
    territorios en un mapa y subir puestos en la clasificación. 

    Estos últimos años la comunidad española ha crecido y se han hecho varios
    intentos de crear una pagina web donde poder ver, subir, y consular los
    resultados de las batallas. Estas páginas no han sido muy buenas ya 
    que son las típicas creadas por plantillas web como "Wix" o "ShinjiForum".

---

* ## Desarrollo de la idea

    Mi idea es crear una aplicación web donde se pueda hacer todo lo 
    anteriormente mencionado además de poder consultar en tiempo real 
    estadisitacas de cada uno de los miembros de los clanes y ver una
    clasificación de los clanes inscritos.

    La aplicación web tambien contaría con un sistema de subida de archivos 
    donde los jugadores puedan subir las repeticiones de las partidas en formato 
    .wotrpl (un formato específico del juego bastante liviano donde cada 
    partida no ocupa mas de unos cuantos Kb).

---

* ## ¿Cómo obtener los datos?

    Al igual que hacen otras páginas web como "tomato.gg" los datos y
    estadísticas de los jugadores pueden ser obtenidos a partir de la API de
    wargaming. (La API no es muy buena pero es lo que hay).

---

* ## Tipos de usuario de la aplicación

    La aplicación contaria con dos tipos de usuarios basicos: <br>

    - Registrados (Permisos de cada uno por desarrollar)
        - Soldado 
        - Oficiales
        - Comandante

    - No registrados
        - Los usuarios no registrados podrian consultar estadísticas de otros 
        clanes y jugadores pero no a las suyas propias. Además no 
        podrían subir repeticiones y tendrían un acceso más limitado a las 
        funcionalidades de la aplicación.

--- 

* ## Detalles de implementacion

    1. Obtener los datos de la API de wargaming y guardar en una base
    de datos propia cosas como: 
        - ID de los clanes/jugadores
        - url del icono de los clanes
        - posición en el ranking
        - nombre de los clanes/jugadores


    2. En la BBDD (Me gustaria MariaDB porque es OpenSource)
        - Tabla clanes
        - Tabla jugadores
        - Tabla batallas

    3. Repeticiones de las partidas
        - Guardar de alguna forma en la base de datos las
        repeticiones que suban los usuarios para que otros
        puedan descargarlas. 

---

* ## Diseño de la aplicación

    Soy de computación entonces no tengo ni idea de como hacer un diseño 
    bonito/útil/sencillo para el usuario. Pero por todos es sabido que los mejores 
    programas son los más feos. Por ejemplo VLC no solo puedo abrir videos o
    imagenes (en cualquier formato de archivo inventados o por inventar), tambien 
    es capaz de traducir texto en arameo.

    Algunas páginas de las que intentré inspirarme serán:
    - https://tomato.gg/
    - https://tanks.gg/
    - https://eu.wargaming.net/clans/wot/leaderboards/#ratingssearch&offset=0&limit=25&order=-cr

    A la hora de mostrar los clanes/jugadores hacerlo con un diseño de
    tabla y otro de cards como en: [modrinth](https://modrinth.com/mods)

    Menu vertical a la izq como en: [TomatoGG](https://tomato.gg/)

    Paginación para que el usuario pueda decidir la cantidad de 
    elementos que se muestren.

    Poder ordenar los elementos de forma ascendente/descendente segun:
    - Su posicion en el ranking
    - Numero de batallas jugadas
    - Mayor daño medio por partida
    - Numero de provincias controladas

