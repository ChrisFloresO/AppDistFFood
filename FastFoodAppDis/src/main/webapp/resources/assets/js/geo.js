function initMap() {
        		var map = new google.maps.Map(document.getElementById('map'), {
          			center: {lat: 18.0858100, lng: -15.9785000},
          			zoom: 3
        		});

	        	if (navigator.geolocation) {
	          		navigator.geolocation.getCurrentPosition(function(position) {
	            	var pos = {
	              		lat: position.coords.latitude,
	              		lng: position.coords.longitude
	            	};
                        /*var iconact = {
                            url: str, // url
                            scaledSize: new google.maps.Size(50, 50), // scaled size
                            origin: new google.maps.Point(0,0), // origin
                            anchor: new google.maps.Point(0, 0) // anchor
                        };
                        var MiUbicacion = new google.maps.Marker({
                            position: pos,
                            map: map,
                            draggable: true,
                            animation: google.maps.Animation.DROP,
                            title: "mi Ubuicaion",
                            icon: iconact

                        });*/
	            	var pos1 = {
		              		lat: -2.887486,
		              		lng: -78.990260
		            };
	            	var pos2 = {
		              		lat: -2.883547,
		              		lng: -78.985157
		            };

	            	var image = 'imagenes/bandera.jpg';
	            	map.setCenter(pos);
	            	map.setZoom(15);
	           		var marker = new google.maps.Marker({
	          			position: pos,
	          			map: map,
	          			draggable: true,
	          			animation: google.maps.Animation.DROP,
	          			//icon: image
	          			label: "A",
	          			title: "Ubicaicon Actual"
	          			
	        		});

	           		if(document.getElementById('tipo').innerText=="admin"){
	           			//alert("Entraaaaaaaaaaaaaaaaaaa");
	           			google.maps.event.trigger(map, 'resize');
	           			map.setCenter(pos);
	           		}
	           		//para leer los datos de la tabla
//	           		alert("entra");
	           		var tab=document.getElementById("formTour:tours");
	           		           		
	           		//alert(tabla);
	           		//gets rows of table
	           	    var rowLength = tab.rows.length;
	           	    var markers=[];
	           	    var infW=[];
	           	   // alert(rowLength);
	           	    //loops through rows    
	           	    for (i = 1; i < rowLength; i++){

	           	      //gets cells of current row  
	           	       var oCells = tab.rows.item(i).cells;

	           	       //gets amount of cells of current row
	           	       //leo los valores de las celdas en las posiciones de latitud y longitud
	           	       //luego convierto dichos valores a float
	           	       var cellLength = oCells.length;
	           	       var la=parseFloat(oCells.item(3).innerHTML);
	           	       var lon=parseFloat(oCells.item(4).innerHTML);
	           	       //guardo los valores en un LatLong
	           	       var posi={
	           	    		  lat: la,
	           	    		  lng: lon
	           	       }
	           	       var nombreLugar=oCells.item(1).innerHTML.trim();//quito los espacios
	           	       var str = oCells.item(8).innerHTML;
     	           //	console.log(str);
	           	       console.log(str.indexOf("value="));
	           	       if(str.indexOf("type=")>str.indexOf("value=")){//si esq se ejecuta en mozilla
	           	    	str=str.substring(str.indexOf("value=")+7,str.indexOf("type=")-2);
	           	       }else{//si se ejecuta en chrome
	           	       str=str.substring(str.indexOf("value=")+7,str.indexOf(">")-1);
	           	    	}
	           	       str=str.replace("jpg","jpeg");
	           	    var icon = {
	           	    	    url: str, // url
	           	    	    scaledSize: new google.maps.Size(50, 50), // scaled size
	           	    	    origin: new google.maps.Point(0,0), // origin
	           	    	    anchor: new google.maps.Point(0, 0) // anchor
	           	    	};
	           	       //agrego el marker al mapa.
        	            markers[i-1]= new google.maps.Marker({
        	            	position: posi,
        	            	map: map,
        	            	draggable: true,
        	            	animation: google.maps.Animation.DROP,
        	            	title: nombreLugar,
        	            	icon: icon
        	            	
        	            });
        	           
        	           console.log(str);
        	           markers[i-1].index=i-1;
        	           var enlace=oCells.item(5).innerHTML;
        	           

        	           
   	    	        infW[i-1] = new google.maps.InfoWindow({
   	    	            content: contentString
   	    	          });
   	    	        google.maps.event.addListener(markers[i-1], 'click', function(){
   	    	        	infW[this.index].open(map,markers[this.index]);
   	    	        	var iwOuter = $('.gm-style-iw');

   	    	         
   	    	         // If the content of infowindow not exceed the set maximum height, then the gradient is removed.
   	    	         if($('.iw-content').height() < 140){
   	    	           $('.iw-bottom-gradient').css({display: 'none'});
   	    	         }

   	    	         // The API automatically applies 0.7 opacity to the button after the mouseout event. This function reverses this event to the desired value.
   	    	         iwCloseBtn.mouseout(function(){
   	    	           $(this).css({opacity: '1'});
   	    	         });
   	    	        });
   	    	        
   	    	     
	           	       for(var j = 0; j < cellLength; j++){

	           	              // get your cell info here

	           	              var cellVal = oCells.item(j).innerHTML;
	           	              
	           	              //alert(cellVal);
	           	           }
	           	    }
	  
	           		google.maps.event.addListener(map, 'click', function(event) {
	           			//alert(tipous);
	           			console.log("consola");
	           			
	           			var tipous=document.getElementById('tipo').innerText;
	           			//alert("entraa");
	           			//alert(document.getElementById('tipo').innerText);
	           			if(tipous=="admin"){
	           				addMarker(event.latLng, map);
	           			}else{
	           				//si es un usuario simple
	           			}
	            	});

	           		function addMarker(location, map) {
            			var marker = new google.maps.Marker({
	            			position: location,
	            		    map: map,
		          			draggable: true,
		          			animation: google.maps.Animation.DROP
	            		});
            			
            			document.getElementById('latitud').value=location.lat();
            			document.getElementById('longitud').value=location.lng();
            			
            			
            			
	            	}
	
	            	
	            	function ruta(location){
	            		var objConfigDR = {
	    	    	           	map: map,
	    	    	        }

	    	    	        var objConfigDS = {
	    	    	    		origin: pos,
	    		    	    	destination: location,
	    			    	    travelMode: google.maps.TravelMode.DRIVING
	    	    			}
	    	    			
	    	           		var directionsService = new google.maps.DirectionsService();
	    					var directionRenderer = new google.maps.DirectionsRenderer(objConfigDR);

	    					directionsService.route(objConfigDS, fnRutear);


	    		      		function fnRutear(resultados, status){
	    						if(status == 'OK'){
	    							directionRenderer.setDirections(resultados);
	    						}else{
	    							alert('error' + status);
	    						}
	    					}	
		            }


	          	//	});//, function() {
	            		//handleLocationError(true, infW, map.getCenter());
	          		});
						          		
	        	} 

	        	//else {
	          		//handleLocationError(false, infW, map.getCenter());
	        	//}
      		}
         	
      		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        		infoWindow.setPosition(pos);
        		infoWindow.setContent(browserHasGeolocation ?
                	'Error: The Geolocation service failed.' :
                    'Error: Your browser doesn\'t support geolocation.');
      		}          	  	