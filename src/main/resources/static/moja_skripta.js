/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const table = document.querySelector("#table");
var pageNumber = table.dataset.pages;
var status = table.dataset.status;



var element =document.getElementById("table_navigation");
  for(var i=0;i<pageNumber;i++){
      var pageLink = document.createElement("a");
      pageLink.setAttribute('href',"users?page="+i+"&status="+status);
      pageLink.innerHTML = i+1;
      element.appendChild(pageLink);
  }  
  
 


