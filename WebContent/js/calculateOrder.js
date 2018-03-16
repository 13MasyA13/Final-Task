function calculate() {
	var from30toMore = document.getElementById("from30toMore").value;
	var from10to30 = document.getElementById("from10to30").value;
	var from4to9 = document.getElementById("from4to9").value;
	var from2to3 = document.getElementById("from2to3").value;
	var pladge = document.getElementById("pladge").value;
	var withDriver = document.getElementById("isWithDriver").value;
	var days = 10;
	var answ;
	if(withDriver == 0){
		if(days <= 3){
			answ = (from2to3 * days) + pladge;
		} else if(days >= 4 && days <= 9){
			answ = (from4to9 * days) + pladge;
		} else if(days >= 10 && days < 30){
			answ = (from10to30 * days) + pladge;
		} else if(days >= 30){
			answ = (from30toMore * days) + pladge;
		}
	} else{
		if(days <= 3){
			answ = (from2to3 * days) + (withDriver * days) + pladge;
		} else if(days >= 4 && days <= 9){
			answ = (from4to9 * days) + (withDriver * days) + pladge;
		} else if(days >= 10 && days < 30){
			answ = (from10to30 * days) + (withDriver * days) + pladge;
		} else if(days >= 30){
			answ = (from30toMore * days) + (withDriver * days) + pladge;
		}
	}

}
