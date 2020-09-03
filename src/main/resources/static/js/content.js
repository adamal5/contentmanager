function displayContent(){
    const req = new XMLHttpRequest();
    req.onreadystatechange = () => {
        // Example handle logic
        if (req.status === 200 && req.readyState === 4) {
            if (req.getResponseHeader("Content-Type") === "application/json") {
                console.log("oh look its some JSON: " + req.responseText);


                let stuff = JSON.parse(req.response);
                stuff.forEach(el => {
                    console.log(el); // prints whole element
                    console.log(el.name); // allows access to specific value

                    // adding title to the body of the page
                    let elem = document.createElement('div');
                    let header = document.createElement('h1');
                    let main = document.createElement("p")
                    header.textContent = "Title: " + el.title;
                    main.textContent = "Content Type: " +el.contentType;
                   /* main.textContent = "Platform: " +el.platform;
                    main.textContent = "Status: " +el.status;
                    main.textContent = "Post Date: " +el.postDate;
                    main.textContent = "Notes: " +el.notes;*/

                    elem.appendChild(header);
                    elem.appendChild(main);

                    el.content.forEach(sponsor => {
                        console.log(sponsor) // print all notes for each notebook
                        let companyName = document.createElement('p');

                        companyName.textContent = "Sponsor: " + sponsor.companyName;

                        elem.appendChild(companyName);
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
    req.open("GET", "http://localhost:8080/content");
    req.send();
}

function submitContent(){
    let elements = document.getElementById("contentForm").elements;
    let obj ={};
    for(let i = 0 ; i < elements.length - 1 ; i++){
        let item = elements.item(i);
        obj[item.name] = item.value;
    }

    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8080/createContent");
    req.onload = () => {
        if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    };
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.send(JSON.stringify({ title: obj.title, contentType: obj.contentType, platform: obj.platform, status: obj.status, postDate: obj.postDate, notes: obj.notes, sponsor:{ sponsorID: Number(obj.sponsorID)} }));
}