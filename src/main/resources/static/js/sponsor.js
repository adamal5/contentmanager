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
                    let body = document.createElement("p")
                    header.textContent = "Company Name: " + el.companyName;
                    body.textContent = "Primary Contact Name: " +el.primaryContactName;
                    body.textContent = "Primary Contact Email: " +el.primaryContactEmail;
                    body.textContent = "Primary Contact Number: " +el.primaryContactPhone;
                    body.textContent = "Notes: " +el.notes;

                    elem.appendChild(header);
                    elem.appendChild(body);

                    el.content.forEach(content => {
                        console.log(content) // print all notes for each notebook
                        let title = document.createElement('p');
                        let status = document.createElement('p');
                        let postDate = document.createElement('p');

                        title.textContent = "Title: " + content.title;
                        status.textContent = "Status: " + content.status;
                        postDate.textContent = "Post Date" + content.postDate;

                        elem.appendChild(title);
                        elem.appendChild(status);
                        elem.appendChild(postDate)
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
    req.open("GET", "http://localhost:8080/getAllNoteBooks");
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
    req.open("POST", "http://localhost:8080/createNote");
    req.onload = () => {
        if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({ companyName: obj.companyName, primaryContactName: obj.primaryContactName, primaryContactEmail: obj.primaryContactEmail, primaryContactPhone: obj.primaryContactPhone,notes: obj.notes, sponsor:{ sponsorID: Number(obj.sponsorID)} }));
}