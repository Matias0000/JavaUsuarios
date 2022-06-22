// Call the dataTables jQuery plugin
$(document).ready(function() {
//    alert(222)
  cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailDelUsuario();
});
function actualizarEmailDelUsuario(){
document.getElementById('txt-email-usuario').outerHTML= localStorage.correo;
}

async function cargarUsuarios(){
    const request = await fetch('api/usuarios',{
    method:'GET',
    headers:getHeaders()
    });
    const usuarios=await request.json();

    let listadoHtml = '';
    for(let usuario of usuarios){
        let botonEliminar='<a href="#" onclick=" eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';


        let telefonoTexto = usuario.telefono == null ? 1 : usuario.telefono;
            let usuarioHtml = '<tr><td>'+usuario.id+'</td><td>' + usuario.nombre+' '+usuario.apellido+'</td><td>'+usuario.correo
                            +'</td><td>'+ usuario.password
                            +'</td><td>'+telefonoTexto
                            +'</td><td>'+ botonEliminar + '</td></tr>';
            listadoHtml += usuarioHtml;
    }
//    let usuarioHTML = '<tr><td>123</td><td>Pruebita</td><td>nro3</td><td>asd@asd.asd</td><td>asd123</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>'
    document.querySelector('#usuarios tbody').outerHTML=listadoHtml
//    console.log(usuarios)

}

function getHeaders(){
return {
       'Accept':'application/json',
       'Content-Type':'application/json',
       Authorization: localStorage.token
       };
}

async function eliminarUsuario(id){

    if(!confirm('¿Desea Eliminar este usuarios?'))
    {
        return;
    }
    const request = await fetch('api/usuarios/' + id,{
        method:'DELETE',
        headers:getHeaders()
       });
    //    document.reload()
       location.reload()
//alert(id)
}