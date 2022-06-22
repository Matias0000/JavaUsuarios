// Call the dataTables jQuery plugin
$(document).ready(function() {
    //    alert(222)

    });
    
async function registrarUsuario(){
    let datos ={};
    datos.nombre=document.getElementById('txtNombre').value
    datos.apellido=document.getElementById('txtApellido').value
    datos.correo=document.getElementById('txtEmail').value
    datos.password=document.getElementById('txtPassword').value
    datos.telefono=document.getElementById('txtTelefono').value
    let repertirPassword=document.getElementById('txtRepeatPassword').value

    if(repertirPassword != datos.password)
    {
        alert('Las Contraseñas son diferentes');
        return;
    }

    const request = await fetch('api/usuarios',{
    method:'POST',
    headers:{
    'Accept':'application/json',
    'Content-Type':'application/json'
    },
    body: JSON.stringify(datos)
    });
    alert("La cuenta fue creada con exito!");
    window.location.href='login.html'

}
    
