<nav class="main_menu text-white space-y-4 pl-3 min-h-screen w-12">
    <div class="mt-3 mr-2">
        <a class="block text-center" href="https://github.com/sergious234">
            <img class="no-invert rounded-full" src="/SPR/menu_icons/Sergious.jpg" alt="alt"/>
            <span class="w-full justify-center text-center">
                Sergious
            </span>
        </a>
    </div>

    
    
    <div class="pt-20">
        <a class="inline flex space-x-3" href="/SPR/home">
            <img src="/SPR/menu_icons/home.svg" alt="alt"/>
            <span class="truncate">Home</span>
        </a>
    </div>
    <div>
        <a class="inline flex space-x-3" href="/SPR/ranking/list">
            <img src="/SPR/menu_icons/ranking.svg" alt="alt"/>
            <span  class="truncate">Ranking</span>
        </a>
    </div>
    <div>
        <a class="inline flex space-x-3" href="/SPR/users/list">
            <img src="/SPR/menu_icons/player.svg" alt="alt"/>
            <span class="truncate">Jugadores</span>
        </a>
    </div>
    <div>
        <a class="inline flex space-x-3" href="/SPR/tanque/crud">
            <img src="/SPR/menu_icons/tank.svg" alt="alt"/>
            <span class="truncate">Tanques</span>
        </a>
    </div>
    <div>
        <a class="inline flex space-x-3" href="/SPR/home">
            <img src="/SPR/menu_icons/clan.svg" alt="alt"/>
            <span class="truncate" >Clanes</span>
        </a>
    </div>
    <% 
        String user = (String) request.getSession().getAttribute("user");
        if (user != null) {
        
            out.println("<div>"
               + "<a class=\"inline flex space-x-3\" href=\"/SPR/users/config\">"
               + "<img src=\"/SPR/menu_icons/config.svg\" alt=\"alt\"/>"
               + "<span class=\"truncate\">" + user + "</span>"
               + "</a>"
                + "</div>");
        } else {
            String x = " <div> "
               + "     <a class=\"inline flex space-x-3\" href=\"/SPR/users/formulario\">"
               + "         <img src=\"/SPR/menu_icons/formulario.svg\" alt=\"alt\"/>"
               + "         <span>Formulario</span>"
               + "     </a>"
               + " </div>";
            out.println(x);
        }
    %>
</nav>