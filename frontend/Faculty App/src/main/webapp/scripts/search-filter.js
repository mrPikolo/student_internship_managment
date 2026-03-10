document.addEventListener("DOMContentLoaded", function(){

    const select = document.querySelector("select[name='searchBy']");
    const input = document.querySelector("input[name='searchValue']");

    if(!select || !input) return;

    select.addEventListener("change", function(){

        input.value = "";
        input.focus();

        if(this.value === "company"){
            input.type = "text";
            input.placeholder = "Enter company name";
        }

        if(this.value === "technologies"){
            input.type = "text";
            input.placeholder = "Enter technology (Java, React...)";
        }

        if(this.value === "startDate"){
            input.type = "date";
            input.placeholder = "";
        }

    });

});