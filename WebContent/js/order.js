$(document).ready(function() {
	
	
	var from30tomore = parseInt($('#from30toMore').val());
	var from10to30 = parseInt($('#from10to30').val());
	var from4to9 = parseInt($('#from4to9').val());
	var from2to3 = parseInt($('#from2to3').val());
	var pladge = parseInt($('#carPledge').val());
	console.log('from30tomore',from30tomore);
	console.log('from10to30', from10to30);
	console.log('from4to9', from4to9);
	console.log('pladge', pladge);

  function dateDiffInDays(a, b) {
    var _MS_PER_DAY = 1000 * 60 * 60 * 24;
    var utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    var utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());
    return Math.floor((utc2 - utc1) / _MS_PER_DAY);
  }

  $("#b").click(function() {
	var firstDate = new Date($('#inputFirstDate').val());
    var secondDate = new Date($('#inputSecondDate').val());
	var withDriver = parseInt($('#inputWithDriver').val());
	var days = dateDiffInDays(firstDate, secondDate);
	console.log('firstDate', firstDate);
	console.log('secondDate', secondDate);
	console.log('withDriver', withDriver);
	console.log('days', days);
	var answer = 0;
		if(withDriver == 0){
			if(days <= 3){
				answer = (from2to3 * days) + pladge;
			} else if(days >= 4 && days <= 9){
				answer = (from4to9 * days) + pladge;
			} else if(days >= 10 && days < 30){
				answer = (from10to30 * days) + pladge;
			} else if(days >= 30){
				answer = (from30toMore * days) + pladge;
			}
		} else{
			if(days <= 3){
				answer = (from2to3 * days) + (withDriver * days) + pladge;
			} else if(days >= 4 && days <= 9){
				answer = (from4to9 * days) + (withDriver * days) + pladge;
			} else if(days >= 10 && days < 30){
				answer = (from10to30 * days) + (withDriver * days) + pladge;
			} else if(days >= 30){
				answer = (from30toMore * days) + (withDriver * days) + pladge;
			}
		}
		console.log(answer);
		$('#answer').val(answer);
		$('#answ').val(answer);
  });
});
