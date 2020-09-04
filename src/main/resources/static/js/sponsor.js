function displaySponsors(){
    const req = new XMLHttpRequest();
    req.onreadystatechange = () => {
        // Example handle logic
        if (req.status === 200 && req.readyState === 4) {
            if (req.getResponseHeader("Content-Type") === "application/json") {
                console.log("oh look its some JSON: " + req.responseText);
                // adding an element to the body example
                // let elem = document.createElement('div');
                // elem.textContent = "hello world";
                // document.body.appendChild(elem);

                let stuff = JSON.parse(req.response);
                stuff.forEach(el => {
                    // console.log(el); // prints whole element
                    // console.log(el.name); // allows access to specific value

                    // adding title to the body of the page
                    let elem = document.createElement('div');
                    let header = document.createElement('h1');
                    let primaryContactName = document.createElement("p")
                    let primaryContactEmail = document.createElement("p")
                    let primaryContactPhone = document.createElement("p")
                    let notes = document.createElement("p")
                    header.textContent = "Company Name: " + el.companyName;
                    primaryContactName.textContent = "Primary Contact Name: " +el.primaryContactName;
                    primaryContactEmail.textContent = "Primary Contact Email: " +el.primaryContactEmail;
                    primaryContactPhone.textContent = "Primary Contact Number: " +el.primaryContactPhone;
                    notes.textContent = "Notes: " +el.notes;

                    elem.appendChild(header);
                    elem.appendChild(primaryContactName);
                    elem.appendChild(primaryContactEmail);
                    elem.appendChild(primaryContactPhone);
                    elem.appendChild(notes);

                    el.content.forEach(content => {
                        console.log(content) // print all notes for each notebook
                        let title = document.createElement('p');
                        let status = document.createElement('p');
                        let postDate = document.createElement('p');

                        title.textContent = "Associated Content: " + content.title;


                        elem.appendChild(title);

                    })
                    document.body.appendChild(elem);
                });
            } else {
                console.log(
                    "Looks like its not JSON but lets see what it is... " + req.responseText
                );
            }
        } else {
            console.log("Oh no... handle error");
        }
    };
    req.open("GET", "http://localhost:8080/sponsor");
    req.send();
}

function submitSponsor(){
    let elements = document.getElementById("sponsorForm").elements;
    let obj ={};
    for(let i = 0 ; i < elements.length - 1 ; i++){
        let item = elements.item(i);
        obj[item.name] = item.value;
    }

    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/createSponsor");
    req.onload = () => {
        if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    alert(obj.companyName);
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({ companyName: obj.companyName, primaryContactName: obj.primaryContactName, primaryContactEmail: obj.primaryContactEmail, primaryContactPhone: obj.primaryContactPhone,notes: obj.notes, content:{ contentID: Number(obj.contentID)} }));
}

//ATTEMPT AT UPDATE FUNCTION

/*
function updateSponsor(sponsorID) {
    sponsorID.forEach( function( sponsor ) {
        updateToSponsor( sponsor.companyName, sponsor.primaryContactName, sponsor.primaryContactEmail, sponsor.primaryContactPhone, sponsor.notes ); // update database
    });

    function updateToSponsor(companyName, primaryContactName, primaryContactEmail, primaryContactPhone, notes){

    }

    const req = new XMLHttpRequest();
    req.open("PUT", "http://localhost:8080/updateSponsor/{id}");
    req.onload = () => {
        if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    
}*/