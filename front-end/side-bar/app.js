const chevron = document.querySelectorAll(".chevron")[0]

chevron.addEventListener('click', (event) => {
    const sidebar = document.getElementsByClassName("side-bar")[0]
    
    if(sidebar.classList.contains("open")){
        sidebar.classList.remove("open")
    } else {
         sidebar.classList.add("open")
    }

});
