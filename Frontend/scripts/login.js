let form = document
  .getElementById("form")
  .addEventListener("submit", (event) => {
    event.preventDefault();
    login();
  });

let login = async () => {
  let loginData = {
    username: document.getElementById("username").value,
    password: document.getElementById("password").value,
  };
  console.log(loginData);

  loginData = JSON.stringify(loginData);

  let loginUrl = "http://localhost:8888/signIn";

  let response = await fetch(loginUrl, {
    method: "POST",

    body: loginData,

    headers: {
      "Content-Type": "application/json",
    },
  });

  let data = await response.json();

  console.log(data);
};
