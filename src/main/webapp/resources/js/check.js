
	function num_check(num)
	{
		if (/^\d+$/.test(document.getElementById("prod"+num).value)){
            document.getElementById("cb"+num).checked = true;
			summ[num] = price[num]*document.getElementById("prod"+num).value;
			calculate_sum();
			return true
		}else
		{
			alert("Введите число!");
			document.getElementById("prod"+num).focus();
			document.getElementById("prod"+num).value = "0";
			summ[num] = 0;
			calculate_sum();
			document.getElementById("cb"+num).checked = false;
			return false		
		} 
	}

    function check_pass(oid) {
        obj = document.getElementById(oid);
        if (obj.value.length > 4) {
			obj.style.border = '1px solid #41a62a';
			obj = document.getElementById(oid+'_err').innerHTML = '';
			return true;                        
        } else {
			showError(obj, oid+'_err', "Минимум 5 символов");
			return false;
        }
    }
	
	function check_name(oid) {
		obj = document.getElementById(oid);
		if (/^[A-Z|a-z|0-9|_]{5,}$/.test(obj.value)){
			obj.style.border = '1px solid #41a62a';
			obj = document.getElementById(oid+'_err').innerHTML = '';
			return true;
		} else {
			showError(obj, oid+'_err', "Допустимы латинские символы и цифры, минимум 5 символов");
			return false;
		}	
	}

	function check_fio(oid) {
		obj = document.getElementById(oid);
		if (/^[A-Z|a-z|А-Я|а-я|0-9| |_|\-]{2,}$/.test(obj.value)){
			obj.style.border = '1px solid #41a62a';
			obj = document.getElementById(oid+'_err').innerHTML = '';			
			return true;
		} else {
			showError(obj, oid+'_err', "Допустимы латинские символы, кириллица и цифры, минимум 2 символа");
			return false;
		}	
	}

	function check_email(oid) {
		obj = document.getElementById(oid);
		if (/^[A-Z|a-z|0-9|_|\-]+\.?[A-Z|a-z|0-9|_|\-]+@{1,1}[A-Z|a-z|0-9|_|\-]+\.?[A-Z|a-z|0-9|_|\-]+\.[A-Z|a-z]{2,4}$/.test(obj.value)){
			obj.style.border = '1px solid #41a62a';
			obj = document.getElementById(oid+'_err').innerHTML = '';			
/*			var val1 = document.getElementById("email").value;
			var val2 = document.getElementById("confirm_email").value;
			if (val1.length>0 && val2.length>0) {
				if (val1 != val2) {
					showError(document.getElementById("email"), "email_err", "Введеный email не совпадает с подтверждением");	
					showError(document.getElementById("confirm_email"), "confirm_email_err", "Введеный email не совпадает с подтверждением");	
					return false;	
				} else {
					obj = document.getElementById("email_err").innerHTML = '';
					obj = document.getElementById("confirm_email_err").innerHTML = '';
					document.getElementById("email").style.border = '1px solid #41a62a';
					document.getElementById("confirm_email").style.border = '1px solid #41a62a';
				}
				
			}*/
		} else {
			showError(obj, oid+'_err', "Не верный формат email");
			return false;
		}	
	}

	function check_mid(oid) {
		obj = document.getElementById(oid);
		if (/^[A-Z|a-z|А-Я|а-я|0-9|_]{0,}$/.test(obj.value)){
			obj.style.border = '1px solid #41a62a';
			obj = document.getElementById(oid+'_err').innerHTML = '';			
			return true;
		} else {
			showError(obj, oid+'_err', "Допустимы латинские символы, кириллица и цифры");
			return false;
		}	
	}

	function check_phone(oid) {
		obj = document.getElementById(oid);
		if (/^[0-9|+\(\)\s]{5,}$/.test(obj.value)){
			obj.style.border = '1px solid #41a62a';
			obj = document.getElementById(oid+'_err').innerHTML = '';			
			return true;
		} else {
			showError(obj,oid+'_err', "Допустимы +, цифры, скобки и пробел");
			return false;
		}	
	}

	function showError(obj, oid, err) {
		document.getElementById(oid).innerHTML = err;
//		alert(err);
		obj.style.border = '1px solid #e42120';
	}