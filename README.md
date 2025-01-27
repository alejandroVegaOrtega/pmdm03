Esta aplicación nos ayuda a saber y seguir un registro facilmente sobre los pokemons capturados y por capturar, y podemos tener fácilmente información sobre los que ya poseemos.  

Las caracteristicas principales de la aplicación consiste en un registro de Pokemons, cargados desde una API externa, mostrados en una pestaña llamada 'Pokedex', donde podremos seleccionar los Pokemons capturados, que aparecerán en otra pestaña llamada 'Capturados' 
y donde podremos ver los diferentes datos de estos haciendo click sobre cada registro que tengamos en la pestaña 'Capturados', tambien podremos eliminar los pokemons capturados haciendo swipe sobre estos, algunos de los datos que podremos ver de los pokemons son:
* Nombre
* Peso
* Altura
* Tipos

Tecnologías y librerias principales de la aplicación:
* Java - Usado para implementar actividades (MainActivity, PokemonDetailsActivity), fragmentos (capturadosFragment) y el patrón de diseño basado en ViewModel.
* Android SDK - Componentes como Activity, Fragment, ViewModel, RecyclerView, TabLayout, y ViewPager2.
* Android Architecture Components:
    * ViewModel: Para gestionar el estado de datos de forma independiente al ciclo de vida de las actividades y fragmentos. Ejemplo: PokemonViewModel.
    * LiveData: Para observar y reaccionar automáticamente a cambios en los datos (por ejemplo, la lista de Pokémon capturados).
* Retrofit - Utilizada para interactuar con una API de Pokémon (como obtener detalles del Pokémon, sus tipos, peso y altura).
* RecyclerView - Componente de UI para mostrar listas de Pokémon
* TabLayout y ViewPager2 - Navegación basada en pestañas
* ItemTouchHelper - Clase de Android utilizada para implementar la funcionalidad de deslizar ítems en la lista (en capturadosFragment) para eliminar Pokémon.
* Gson - Manejo de respuestas JSON de la API de Pokémon.
* AndroidX - Componentes como ViewModel, LiveData, RecyclerView, ViewPager2 y Fragment.
* Activity Result API - Manejo de eventos entre capturadosFragment y PokemonDetailsActivity.

Respecto a la aplicación, no está acabada, falta bastante trabajo aun, la iré actualizando a medida que pueda avanzar, he tenido muchos problemas con la implementación de la autentificación y el guardado de datos.
