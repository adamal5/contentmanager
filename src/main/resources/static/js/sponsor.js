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
                    elem.setAttribute("class", "container")
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
    req.open("GET", "http://35.246.79.253:8080/sponsor");
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
    req.open("POST", "http://35.246.79.253:8080/createSponsor");
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


function updateSponsor() {

    let elements = document.getElementById("updateSponsorForm").elements;
    let update ={};
    for(let i = 0 ; i < elements.length - 1 ; i++){
        let item = elements.item(i);
        update [item.name] = item.value;
    }

    const url = "http://35.246.79.253:8080/updateSponsor/" + update.sponsorID;

    const req = new XMLHttpRequest();
    req.open("PUT", url);
    req.onload = () => {
        if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({ companyName: update.companyName, primaryContactName: update.primaryContactName, primaryContactEmail: update.primaryContactEmail, primaryContactPhone: update.primaryContactPhone,notes: update.notes, content:{ contentID: Number(update.contentID)} }));
    
}

function deleteSponsor(){

    const sponsorID = document.getElementById("sponsorID");

   /*let elements = document.getElementById("deleteSponsorForm").elements;
    let remove ={};
    for(let i = 0 ; i < elements.length - 1 ; i++){
        let item = elements.item(i);
        remove [item.name] = item.value;
    }*/

    let urldelete = "http://35.246.79.253:8080/deleteSponsor/"+remove.sponsorID;

    const req = new XMLHttpRequest();
    req.open("DELETE", urldelete);
    req.onload = () => {
        if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    req.send();
}