/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
     
        function sprawdzIloscIznaczniki() {
           var il = document.getElementById("add_karta_i_przyjecie:ilosc");
           var pocz = document.getElementById("add_karta_i_przyjecie:pocz");
           var koniec = document.getElementById("add_karta_i_przyjecie:koniec");
           var znMess = document.getElementById("add_karta_i_przyjecie:znMess");
           var dostepny = document.getElementById("add_karta_i_przyjecie:dostepny_input");
           
           
           //alert( il.value  + " " + pocz.value + " " + koniec.valueOf() );
           
           var s = "";
           
           if( dostepny.checked ) {
                var ilZnacz = Math.abs( Number(pocz.value) - Number(koniec.value)); 
                if(  ilZnacz != Number(il.value) )
                    s = "Problem. Ilość wynikająca ze znaczników (" + ilZnacz + ") różna od ilości wprowadzonej";
           }
           
           znMess.innerHTML = s;
           
           
           //if( koniec - pocz == 0 )
               //alert( s );
        };
        
        function g() {
            
        };
        
