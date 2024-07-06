var mid_operator = "";
var is_before_input = "";
var tmp = "";
var error_msg = "";
var memory_num = 0;
function memory_calc(input_memory_key){
	//計算結果の数値を格納する
	result_num = parseFloat(document.getElementById("id").innerText);
	if(input_memory_key == "M+"){
		memory_num += result_num;
	}else if(input_memory_key == "M-"){
		memory_num -= result_num;
	}else if(input_memory_key == "MC"){
		memory_num = 0;
	}else{
		//MRが入力された場合
		result_write(memory_num);
	}
	is_before_input = input_memory_key;
}
function operator_calc(input_operater){
    //演算子を2回連続でクリックされた時はエラーを表示
    if(is_before_input == "+" || is_before_input == "-" || is_before_input == "/" || is_before_input == "*"){
        if(input_operater != "AC"){
            error_msg_write("数値を選択してください");
            return;
        }
    }
    error_msg = "";
    var input_data = document.getElementById("id").innerText;
    //入力された値が小数点だった場合
    if(input_operater == "."){
        //小数点が置けるか判断するための変数
        can_use_dot = input_data.indexOf(".");
        if(can_use_dot == -1){
            //document.getElementById("id").innerText = document.getElementById("id").innerText + ".";
            document.getElementById("id").innerText += ".";
        }else{
            error_msg = "ここにドットを置けません";
        }
    }else if(input_operater == "AC"){
        mid_operator = "";
        tmp = "";
        can_use_dot = true;
        error_msg = "";
        result_write("0");
    }else if(input_operater == "%"){     

        result_write(input_data * 100);

    }else if(input_operater == "+/-"){

        result_write(-(input_data));

    }else if(input_operater == "="){
        if(tmp != ""){
            if(input_operater == "/" && tmp == "0"){
                error_msg_write("0で割り算は行えません");
                return;
            }
            formula = tmp + mid_operator + input_data;
            tmp = "";
            result_write(eval(formula));
        }
        mid_operator = "";
    //四則演算が選択された場合
    }else{
        //0で割り算をしていないか確認
        if(input_operater == "/"){
            if(tmp == "0" || input_data == "0"){
                error_msg_write("0で割り算は行えません");
                return;
            }
        }
        if(tmp == ""){
            tmp = input_data;
        }else{
            formula = tmp + mid_operator + input_data;
            tmp = eval(formula);
            result_write(tmp);
        }
        mid_operator = input_operater;
    }
    is_before_input = input_operater;
    error_msg_write(error_msg);
}

//数値が選択された場合に実行される
function num_calc(num){
    if(is_before_input == "+" || is_before_input == "-" || is_before_input == "/" || is_before_input == "*"){
        result_write(num);
    }else{
        result_num = parseFloat(document.getElementById("id").innerText + num);
        result_write(result_num);
    }
    is_before_input = num;
    error_msg_write("");
}
//選択された数値を計算結果出力欄に表示する
function result_write(result_input){
    document.getElementById("id").innerText = result_input;
}
//引数で受け取った文字列をエラーメッセージ出力欄に表示する
function error_msg_write(msg){
    document.getElementById("error_msg").innerText = msg;
}
