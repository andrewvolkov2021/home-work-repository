<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Добавление сотрудников</title>
    </head>
    <body>

        <h1>База данных Employees</h1>
        <h2>Добавление сотрудника в базу данных</h2>
        <form id="2" action="api/employee" method="POST">
            <p>Для добавления сотрудника в базу данных необходимо указать обязательные параметры: имя и зарплату</p>
            <p>Имя :</p><input name="name" type="text" placeholder="Введите имя сотрудника" size="50">
            <p>Зарплата:</p><input name="salary" type="number"  min="0" max="99999999.99" step="0.01" placeholder="Зарплата" size="50">
            <br><br><br>
            <input type="submit" value="Добавить"/>
            <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee/getId';" value="Карточка сотрудника">
        </form>

        <script type="text/javascript">
			document.getElementById('2').addEventListener('submit', submitForm);
			function submitForm(event) {
			    // Отменяем стандартное поведение браузера с отправкой формы
			    event.preventDefault();
			    // event.target — это HTML-элемент form
			    let formData = new FormData(event.target);
			    // Собираем данные формы в объект
			    let obj = {};
			    formData.forEach(
			    	(value, key) => {
						if(key.includes('.')){
							var partName = key.split(".");
			    			obj[partName[0]] = {};
			    			obj[partName[0]][partName[1]] = value;
			    		} else {
							obj[key] = value;
			    		}
			    	}
			    );
			    // Собираем запрос к серверу
			    let request = new Request(event.target.action, {
			        method: 'POST',
			        body: JSON.stringify(obj),
			        headers: {
			            'Content-Type': 'application/json',
			        },
			    });
			    // Отправляем (асинхронно!)
			    fetch(request).then(
			        function(response) {
			            // Запрос успешно выполнен
			            console.log(response);
			            // return response.json() и так далее см. документацию
			        },
			        function(error) {
			            // Запрос не получилось отправить
			            console.error(error);
			        }
			    );
			    // Код после fetch выполнится ПЕРЕД получением ответа
			    // на запрос, потому что запрос выполняется асинхронно,
			    // отдельно от основного кода
			    console.log('Запрос отправляется');
			}
		</script>

        <hr>

        <h2>Автомантическое заполнение базы данных</h2>
        <p>Для автоматического заполнения базы данных необходимоуказать количество сотрудников</p>
        <form id="1" action="api/autoFiller" method="POST">
            <p>Количество сотрудников:</p><input name="count" type="number" placeholder="Количество" size="50">
            <br><br>
            <input type="submit" value="Добавить сотрудников"/>
        </form>

        <script type="text/javascript">
			document.getElementById('1').addEventListener('submit', submitForm1);
			function submitForm1(event) {
			    // Отменяем стандартное поведение браузера с отправкой формы
			    event.preventDefault();
			    // event.target — это HTML-элемент form
			    let formData = new FormData(event.target);
			    // Собираем данные формы в объект
			    let obj = {};
			    formData.forEach(
			    	(value, key) => {
						if(key.includes('.')){
							var partName = key.split(".");
			    			obj[partName[0]] = {};
			    			obj[partName[0]][partName[1]] = value;
			    		} else {
							obj[key] = value;
			    		}
			    	}
			    );
			    // Собираем запрос к серверу
			    let request = new Request(event.target.action, {
			        method: 'POST',
			        body: JSON.stringify(obj),
			        headers: {
			            'Content-Type': 'application/json',
			        },
			    });
			    // Отправляем (асинхронно!)
			    fetch(request).then(
			        function(response) {
			            // Запрос успешно выполнен
			            console.log(response);
			            // return response.json() и так далее см. документацию
			        },
			        function(error) {
			            // Запрос не получилось отправить
			            console.error(error);
			        }
			    );
			    // Код после fetch выполнится ПЕРЕД получением ответа
			    // на запрос, потому что запрос выполняется асинхронно,
			    // отдельно от основного кода
			    console.log('Запрос отправляется');
			}
		</script>

        <hr>
        <p>Получить из базы список отделов/должностей/сотрудников </p>
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/department';" value="Список отделов">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/position';" value="Список должностей">
        <input type="button" onclick="location.href='/Mk-JD2-82-21-employees-1.0-SNAPSHOT/employee?paramEmployee=${'newPage'}';" value="Список сотрудников">

    </body>
</html>