const cool = require('cool-ascii-faces')
const express = require('express')
const path = require('path')
const PORT = process.env.PORT || 5000
var app=express();
//ayuda a codificar los parametros que vengan a partir del metodo post 
//o apartir de las url

var bodyPaser = require('body-parser');
app.use(bodyPaser.urlencoded({extended : true})); //Soporte para decodificar las url
app.use(bodyPaser.json());//soporte para codificar json

//coneccion a firabase conexcion base de datos
var admin = require("firebase-admin");

 var serviceAccount = require("./course4week3hw1petagram-firebase-adminsdk-wqhh2-14c8b20b86.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://course4week3hw1petagram.firebaseio.com"
});
//End coneccion a firabase conexcion base de datos
//library for sending notification to firebase cloud message
var FCM = require('fcm-push');

var tokenDevicesURI = "registrar-usuario";
app
  .use(express.static(path.join(__dirname, 'public')))
  .set('views', path.join(__dirname, 'views'))
  .set('view engine', 'ejs')
  .get('/', (req, res) => res.render('pages/index'))
  .get('/cool', (req, res) => res.send(cool()))
//POST
//https://secure-basin-36492.herokuapp.com/registrar-usuario
//token
  .post('/'+tokenDevicesURI,function(request,response){
    var id_dispositivo =    request.body.id_dispositivo;
    var id_usuario_instagram =    request.body.id_usuario_instagram;


    //traer la base de datos referencia de firebase 
    var db = admin.database();
    //insertar
    //como quiero que empiece a cojer las claves  apartir de registrar-usuario
    //sin push sobre escribe el mismo registro en la base de datos.
  var tokenDevices = db.ref(tokenDevicesURI).push();
                      //estructura de la base de datos 
                      tokenDevices.set({
                        id_dispositivo: id_dispositivo,
                        id_usuario_instagram: id_usuario_instagram
                      });
  
//idnetificador de database
//https://secure-basin-36492.herokuapp.com/registrar-usuario/-LKXhG0Ys39PKoNAzwev
  var path = tokenDevices.toString();
  var pathSplit = path.split(tokenDevicesURI +"/")
  var idAutoGenerado = pathSplit[1];
//traer hijo added
    var respuesta = generarRespuestaToken(db,idAutoGenerado);

 //emviar notificacion desde el servidor fcm al cell throught the fcm
 enviarNotificacion(respuesta.id_dispositivo,"COURSERA PROBANDO MENSAJE");

    //the respond will  type json
    response.setHeader("Content-Type","application/json");
   response.send(JSON.stringify(respuesta));
    
    // var pru={
    //   id: "",
    //   token: "sd"
    //         };    
    // response.send(JSON.stringify(pru));

   

  });

  function generarRespuestaToken(db,idAutoGenerado){
      var usuario_instagram ={};
      var usuario ="";
      //tokenDevicesURI = "registrar-usuario";
      var ref = db.ref(tokenDevicesURI);
      // last record added
      ref.on("child_added",function(snapshot, prevChildKey){
        // var newPost= snapshot.val();
         usuario= snapshot.val();
          usuario_instagram = {
              id: idAutoGenerado,
              id_dispositivo: usuario.id_dispositivo,
              id_usuario_instagram:usuario.id_dispositivo
            
          };

        // console.log("Author: " + newPost.author);
        // console.log("Title: " + newPost.title);
        // console.log("Previous Post ID: " + prevChildKey);
      });
      return usuario_instagram;
  }




  //Enviar Notificacion al mismo  ultimo celular que se registro.
  //enviando notificacion al servidor FCM FIREBASE CLOUD MESSAGE
//PARA ESTO SE UTILIZARA UN ALIBRERIA QUE YA ESTALISTA EN NODEJS
//library https://www.npmjs.com/package/fcm-push
function enviarNotificacion(tokenDestino,mensaje){
  
  var serverKey = 'AAAA2hwOb9s:APA91bFJNJzXme-R7fVuhipe7RY8AGg2AiO4DpXHsAX5HKnzpSTLTLaG1qv9ijQUPAZkjIiOo_2s7EArKwhohbKbGX2LCJ3LRv-9_lITRMFvdrW3wkqlJ604qKNTuHe_-E-rokfrtHjW';
  var fcm = new FCM(serverKey);

  var message = {
    to: tokenDestino, // required fill with device token or topics
    collapse_key: '', 
    data: {},
    notification: {
        title: 'Notificacion Desde el servidor',
        body: mensaje,
        sound:"default",
        color:"#C1D8EF"

    }
};

//callback style
fcm.send(message, function(err, response){
    if (err) {
        console.log("Something has gone wrong!");
    } else {
        console.log("Successfully sent with response: ", response);
    }
});

//promise style
fcm.send(message)
    .then(function(response){
        console.log("Successfully sent with response: ", response);
    })
    .catch(function(err){
        console.log("Something has gone wrong!");
        console.error(err);
    })

}



  app.listen(PORT, () => console.log(`Listening on ${ PORT }`))
