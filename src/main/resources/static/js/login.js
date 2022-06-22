// Call the dataTables jQuery plugin
$(document).ready(function() {
    //    alert(222)

    });

async function iniciarSesion(){
    let datos ={};
    datos.correo=document.getElementById('InputEmail').value
    datos.password=document.getElementById('InputPassword').value


    const request = await fetch('api/login',{
    method:'POST',
    headers:{
    'Accept':'application/json',
    'Content-Type':'application/json'
    },
    body: JSON.stringify(datos)
    });
    const response = await request.text();
    if(response!='FAIL'){
    localStorage.token=response;
    localStorage.correo=datos.correo;
    window.location.href='usuarios.html'
    }
    else{
    alert('Usuario o contraseña Incorrectos')
    }
}

