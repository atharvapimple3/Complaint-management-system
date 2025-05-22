// function logout() {
//   sessionStorage.clear();
//   sessionStorage.setItem("loggedOut", "true");
//   window.location.href = "index.html";
// }


document.getElementById("login").addEventListener("submit",function(e){
  e.preventDefault();
  verify();
});

// document.addEventListener("DOMContentLoaded", function () {
//   const submit = document.getElementById("submit");
//   const loginForm = document.getElementById("login");
//   if (loginForm) {
//     loginForm.addEventListener("submit", function (e) {
//       e.preventDefault();
//       verify();
//     });
//   }
  
  function verify() {
    
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    fetch("http://localhost:8080/auth/signin",{
      method:"POST",
      headers:{
        "Content-Type":"application/json",
      },
      body: JSON.stringify({username: email , password : password})
    })
      .then(response => {
        // Handle HTTP status codes with console logs
        if (response.status === 200) {
          return response.json();
        } else if (response.status === 404) {
          document.getElementById("error").textContent = "404 Not Found - Users data not available.";
        } else if (response.status === 401) {
          document.getElementById("error").textContent = "401 Unauthorized - Invalid token or access denied.";
        } else if (response.status === 500) {
          document.getElementById("error").textContent = "500 Internal Server Error.";
        } else {
          document.getElementById("error").textContent = `Unexpected status code: ${response.status}`;
        }
      })
     .then(response => {
      if (!response) return;

      if (response.token)
        localStorage.setItem("token", response.token);
      else
        throw new Error("Token Not Found");

      const userType = getUserType();
      if (userType === "ADMIN") {
        window.location.href = "adminLogin.html";
      } else {
        window.location.href = "userLogin.html";
      }
    }
  )
  //     .then(users => {
  //       const user = users.find(u => u.email === email && u.password === password);
  //       if (user) {
  //         sessionStorage.setItem("userId", user.userId);
  //         sessionStorage.setItem("userName", user.name);
  //         sessionStorage.setItem("userType", user.userType);
  //         sessionStorage.setItem("password",user.password);
  //         if (user.userType === "Admin" || user.userType === "admin") {
  //           window.location.href = "adminLogin.html";
  //         } else {
  //           window.location.href = "userLogin.html";
  //         }
  //       } else {
  //         document.getElementById("error").textContent = "Invalid email or password.";
  //       }
  //     })
  //     .catch(err => {
  //       console.error("Error fetching users:", err);
  //     });
  // }
  
  // function getUserId() {
  //   return sessionStorage.getItem("userId");
  // }
  
  // function getUserName() {
  //   return sessionStorage.getItem("userName");
  // }
  
  // function getUserType() {
  //   return sessionStorage.getItem("userType");
  // }

  // function getBasicAuth(){
  //   const username = sessionStorage.getItem("username");
  //   const password = sessionStorage.getItem("password");
  //   const basicAuth = btoa(`${username}:${password}`);

  //   return `Basic (${basicAuth})`;
  // }
  
  
};

  