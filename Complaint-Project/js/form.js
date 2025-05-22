const URL = "http://localhost:8080/auth";
const form = document.getElementById("regForm");
const username = document.getElementById("username");
const email = document.getElementById("email");
const number = document.getElementById("number");
const submit = document.getElementById("submit");
const password = document.getElementById("password");
// const usertype = document.getElementById("usertype");

document.getElementById("regForm").addEventListener("submit", function(e){
    e.preventDefault();

    const valid = validateUsername() & validatePassword() & validateMobile() & validateEmail() & confirmPasswordValidate();

    if(valid){
        const user = {
            name: username.value.trim(),
            email: email.value.trim(),
            password: password.value.trim(),
            phone: Number(number.value),
            userType: "USER"
        };

        fetch(`${URL}/register`,{
            method: "POST",
            headers:{ "Content-Type" : "application/json"},
            body: JSON.stringify(user)
        })
        .then(response => {
            if(response.status === 201){
                alert("User registered successfully!");
                form.reset();
                window.location.href="index.html";
            }else if(response.status === 409){
                    alert("Email already exists!");
                    document.getElementById("email").classList.add("is-invalid");
            }
        })
        .catch(error =>{
            console.log("Error: "+error);
        });
    }
});

function validateUsername(){
    if(username.value.trim().length > 2){
        username.classList.add("is-valid");
        username.classList.remove("is-invalid");
        return true;
    }else{
        username.classList.add("is-invalid");
        username.classList.remove("is-valid");
        return false;
    }
}
function validateEmail(){
    
    if(email.value && email.checkValidity()){
        email.classList.add("is-valid");
        email.classList.remove("is-invalid");
        return true;
    }else{
        email.classList.add("is-invalid");
        email.classList.remove("is-valid");
        return false;
    }
}
function validatePassword(){
    const passVal = password.value;
    const passRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}$/;
    if (passRegex.test(passVal)) {
      password.classList.add("is-valid");
      password.classList.remove("is-invalid");
      return true;
    } else {
      password.classList.add("is-invalid");
      password.classList.remove("is-valid");
      return false;
    }
}
function confirmPasswordValidate(){
    const confirmpassword = document.getElementById("confirmpassword");
    const cpass = confirmpassword.value;
    const pass = password.value;
    if(cpass === pass){
        confirmpassword.classList.add("is-valid");
        confirmpassword.classList.remove("is-invalid");
        return true;
    }else{
        confirmpassword.classList.add("is-invalid");
        confirmpassword.classList.remove("is-valid");
        return false;
    }
}

function validateMobile() {
    const mobileRegex = /^[789]\d{9}$/;
    if (mobileRegex.test(number.value.trim())) {
      number.classList.add("is-valid");
      number.classList.remove("is-invalid");
      return true;
    } else {
      number.classList.add("is-invalid");
      number.classList.remove("is-valid");
      return false;
    }
}

// function validateUserType(){
//     if(usertype.value){
//         usertype.classList.add("is-valid");
//         usertype.classList.remove("is-invalid");
//         return true;
//     }else{
//         usertype.classList.add("is-invalid");
//         usertype.classList.remove("is-invalid");
//         return false;
//     }
// }

  
