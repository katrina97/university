// Проверка на корректность формата даты (yyyy-mm-dd)
        function isValidDateFormat(dateString) {
    var regex = /^\d{4}-\d{2}-\d{2}$/;
    if (!regex.test(dateString)) return false; // Проверка формата даты

    var parts = dateString.split('-');
    var year = parseInt(parts[0]);
    var month = parseInt(parts[1]);
    var day = parseInt(parts[2]);

    if (month < 1 || month > 12) return false; // Проверка корректности месяца
    if (day < 1 || day > 31) return false; // Проверка корректности дня

    // Проверка корректности даты с учетом високосных годов и числа дней в месяце
    var daysInMonth = new Date(year, month, 0).getDate();
    if (day > daysInMonth) return false;

    return true;
}


        function checkAndAddStudent() {

    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var birthDate = document.getElementById("birthDate").value;
    var admissionDate = document.getElementById("admissionDate").value;
    var faculty = document.getElementById("faculty").value;
    var email = document.getElementById("email").value;


// Проверка на заполненность полей и корректность формата даты
if (firstName && lastName && isValidDateFormat(birthDate) && isValidDateFormat(admissionDate) && faculty && email) {
// Показываем надпись "loading..."
            document.getElementById('loading').style.display = 'block';

    var student = {
        firstName: firstName,
        lastName: lastName,
        birthDate: birthDate,
        admissionDate: admissionDate,
        faculty: faculty,
        email: email
    };




            function createFireworks(numFireworks) {
    for (let i = 0; i < numFireworks; i++) {
        createFirework();
    }
}
            function createFirework() {
        var firework = document.createElement('div');
        firework.classList.add('firework');

        // Расположение салюта в разных частях экрана
    var x = Math.random() * window.innerWidth;
    var y = Math.random() * window.innerHeight;
    firework.style.left = x + 'px';
    firework.style.top = y + 'px';
    firework.style.zIndex = '2'; // Устанавливаем z-index больше

        document.getElementById('fireworkContainer').appendChild(firework);
    }



            // Создание и добавление звукового элемента
    var fireworkSound = new Audio('media/piro.mp3');

            fetch('/students', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(student)
            })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                fetchStudents();
                createFireworks(10);
                fireworkSound.play();

                 // Скрываем надпись "loading..."
                                document.getElementById('loading').style.display = 'none';


                  // Создание и добавление картинки
            var image = document.createElement('img');
            image.src = 'images/congratulation.png'; // Укажите путь к вашей картинке
            image.style.width = '900px'; // Укажите желаемую ширину картинки
            image.style.height = '300px'; // Укажите желаемую высоту картинки
            image.style.zIndex = '1'; // Устанавливаем z-index меньше

            // Задание позиции картинки в центре страницы
image.style.position = 'fixed'; // Фиксированная позиция
image.style.top = '50%'; // По вертикали в середине
image.style.left = '50%'; // По горизонтали в середине
image.style.transform = 'translate(-50%, -50%)'; // Центрирование относительно центра

            document.getElementById('imageContainer').appendChild(image);

            })
            .catch((error) => {
                console.error('Error:', error);
            });
             } else {
            // Вывод сообщения об ошибке
            alert('Please fill out all fields correctly and make sure the date is in the format yyyy-mm-dd');
        }
    }



        function fetchStudents() {
            fetch('/students')
            .then(response => response.json())
            .then(data => {
                console.log('Students:', data);
                data.forEach(student => {
                    var listItem = document.createElement('li');
                    listItem.textContent = student.firstName + ' ' + student.lastName  + ' ' + student.faculty + ' ' + student.email;
                    document.getElementById("studentListHeading").appendChild(listItem);
                });
            })
            .catch((error) => {
                console.error('Error:', error);
            });
            // Очистка полей формы после добавления студента
            document.getElementById("firstName").value = "";
            document.getElementById("lastName").value = "";
            document.getElementById("birthDate").value = "";
            document.getElementById("admissionDate").value = "";
            document.getElementById("faculty").value = "";
            document.getElementById("email").value = "";

                document.getElementById("studentListHeading").textContent = 'Students List';
        }



        // Обработчики событий для проверки формата даты при вводе
document.getElementById("birthDate").addEventListener("input", function() {
    var birthDate = this.value;
    if (!isValidDateFormat(birthDate)) {
        this.classList.add("invalid-date"); // Добавляем класс для красной рамки
    } else {
        this.classList.remove("invalid-date"); // Убираем класс, если дата корректна
    }
});

document.getElementById("admissionDate").addEventListener("input", function() {
    var admissionDate = this.value;
    if (!isValidDateFormat(admissionDate)) {
        this.classList.add("invalid-date"); // Добавляем класс для красной рамки
    } else {
        this.classList.remove("invalid-date"); // Убираем класс, если дата корректна
    }
});

window.unload = fetchStudents;