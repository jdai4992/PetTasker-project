function sortLowToHigh(parent, childSelector, keySelector) {
    var items = parent.children(childSelector).sort(function(a, b) {
        var vA = $(keySelector, a).text();
        var vB = $(keySelector, b).text();
        return (vA < vB) ? -1 : (vA > vB) ? 1 : 0;
    });
    parent.append(items);
}

function sortHighToLow(parent, childSelector, keySelector) {
    var items = parent.children(childSelector).sort(function(a, b) {
        var vA = $(keySelector, a).text();
        var vB = $(keySelector, b).text();
        return (vA > vB) ? -1 : (vA < vB) ? 1 : 0;
    });
    parent.append(items);
}

function sortNumHighToLow(parent, childSelector, keySelector) {
    var items = parent.children(childSelector).sort(function(a, b) {
        var vA = parseInt($(keySelector, a).text());
        var vB = parseInt($(keySelector, b).text());
        return (vA > vB) ? -1 : (vA < vB) ? 1 : 0;
    });
    parent.append(items);
}

function sortNumLowToHigh(parent, childSelector, keySelector) {
    var items = parent.children(childSelector).sort(function(a, b) {
        var vA = parseInt($(keySelector, a).text());
        var vB = parseInt($(keySelector, b).text());
        return (vA < vB) ? -1 : (vA > vB) ? 1 : 0;
    });
    parent.append(items);
}


var sortBy = ""
var asc = true
function sortDate(parent, childSelector, keySelector) {
  if (sortBy == keySelector) { 
	  asc = !asc 
  } else { 
	  asc = true 
  }
  sortBy = keySelector
  var items = parent.children(childSelector).sort(function(a, b) {
      var vA = $.trim($(keySelector, a).text());
      var vB = $.trim($(keySelector, b).text());        
      return ((vA > vB) ? -1 : (vA < vB) ? 1 : 0) * (asc ? 1 : -1);          
  });
  
  parent.empty().append(items);
}

/* setup sort attributes */
$('#filterInput').data("name", "span.taskName");
$('#filterInput').data("price", "span.taskPrice");
$('#filterInput').data("startDate", "span.taskStartDate");

/* sort on button click */
$("#filterInput").change(function() {
   $(".completedTask").show();
   $(".openTask").show();
   $(".assignedTask").show();
   var filterInput = document.getElementById("filterInput");   
   // get the selected element from the drop down menu
   if (filterInput.value == "Newly Added") {
	   sortDate($('#row'), "div", $(this).data("startDate"));
   } else if (filterInput.value == "Older") {
	   sortDate($('#row'), "div", $(this).data("startDate"));
   } else if (filterInput.value == "Price (Low to High)") {
	   sortNumLowToHigh($('#row'), "div", $(this).data("price"));
   }	 else if (filterInput.value == "Price (High to Low)") {
	   sortNumHighToLow($('#row'), "div", $(this).data("price"));
   } else if (filterInput.value == "Name (Ascending)") {
	   sortLowToHigh($('#row'), "div", $(this).data("name"));
   }	 else if (filterInput.value == "Name (Descending)") {
	   sortHighToLow($('#row'), "div", $(this).data("name"));
   }	 else if (filterInput.value == "Active Tasks") {
		var completedTask = document.getElementsByClassName("completedTask");
		var assignedTask = document.getElementsByClassName("assignedTask");
		for (var i = 0; i < completedTask.length; i++) {
			$(".completedTask").hide();
		}
		for (var i = 0; i < assignedTask.length; i++) {
			$(".assignedTask").hide();
		}
   } else if (filterInput.value == "Inactive Tasks") {
		var openTask = document.getElementsByClassName("openTask");
		var assignedTask = document.getElementsByClassName("assignedTask");
		for (var i = 0; i < openTask.length; i++) {
			$(".openTask").hide().slideUp(300);
		}
   }
});

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//REFERENCES
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//References used were:
//
//- div, S. (2017). Sorting parent div by content in child div. [online] Stackoverflow.com. 
//Available at: https://stackoverflow.com/questions/20153533/sorting-parent-div-by-content-in-child-div [Accessed 15 Oct. 2017].