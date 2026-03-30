const resultContainer = document.getElementById("ResultValue");
const inputContainer = document.getElementById("InputValue");

function callWS(msgtosend) {
 /*1*/const socketWS =
    new WebSocket("ws://localhost:8080/eval");

 /*2*/socketWS.onopen = () => {
    console.log("callWS | Connesso a eval");
    socketWS.send(msgtosend);
  }

 /*3*/socketWS.onmessage = (event) => {
    console.log("callWS | onmessage:", event.data);
    resultContainer.innerHTML = event.data;
  }
}//callWS

async function callHTTPGet() {
  try {
    const url = `http://localhost:8080/eval?x=${inputContainer.value}`;
    const response = await fetch(url);

    // Controlla se il server ha risposto con un errore (es. 404 o 500)
    if (!response.ok) {
      throw new Error(`Errore del server: ${response.status}`);
    }
    //const data = await response ;
    const data = await response.json(); // Converte la risposta da JSON a oggetto JS
    console.log('testHTTPGet | Dati ricevuti con successo:', data);
    resultContainer.innerHTML = data;
  } catch (error) {
    console.error('testHTTPGet | Errore:', error);
  }
}

async function callHTTPPost() {
  try {
    const url = 'http://localhost:8080/evaluate';
    const response = await fetch(url, {
      method: 'POST', // Specifica il metodo
      headers: {
        'Content-Type': 'application/json' // Dice al server che stiamo inviando JSON
        // 'Authorization': 'Bearer IL_TUO_TOKEN' // Aggiungi questo se serve autenticazione
      },
      body: JSON.stringify({ x: inputContainer.value }) // Converte l'oggetto JS in una stringa JSON
    });
    console.log('testHTTPPost | Sent:');

    const result = await response.json();
    console.log('testHTTPPost |  result=', result);
    resultContainer.innerHTML = result.result;
  } catch (error) {
    console.error('testHTTPPost | Errore durante il caricamento:', error);
  }
}