const API = "/students";

function loadAll() {
    fetch(API)
        .then(res => res.json())
        .then(render)
        .catch(err => console.error(err));
}

function addStudent() {
    const name = document.getElementById("name").value.trim();
    const age = document.getElementById("age").value;
    const gpa = document.getElementById("gpa").value;

    if (!name || !age || !gpa) {
        alert("Fill all fields");
        return;
    }

    fetch(API, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            age: Number(age),
            gpa: Number(gpa)
        })
    })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            loadAll();
        })
        .catch(err => console.error(err));
}

function deleteStudent(id) {
    fetch(`${API}/${id}`, {
        method: "DELETE"
    })
        .then(() => loadAll())
        .catch(err => console.error(err));
}

function highGpa() {
    const min = document.getElementById("minGpa").value || 3.5;

    fetch(`${API}/high-gpa/${min}`)
        .then(res => res.json())
        .then(render)
        .catch(err => console.error(err));
}

function sortByAge() {
    fetch(`${API}/sort/age`)
        .then(res => res.json())
        .then(render)
        .catch(err => console.error(err));
}
const COURSE_API = "/courses";

function loadCourses() {
    fetch(COURSE_API)
        .then(res => res.json())
        .then(renderCourses)
        .catch(err => console.error(err));
}

function addCourse() {
    const title = document.getElementById("courseTitle").value.trim();
    const credits = document.getElementById("courseCredits").value;

    if (!title || !credits) {
        alert("Fill all fields");
        return;
    }

    fetch(COURSE_API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            title: title,
            credits: Number(credits)
        })
    })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            loadCourses();
        });
}

function deleteCourse(id) {
    fetch(`${COURSE_API}/${id}`, {
        method: "DELETE"
    }).then(() => loadCourses());
}
function assignCourse() {
    const studentId = document.getElementById("studentId").value;
    const courseId = document.getElementById("courseId").value;

    if (!studentId || !courseId) {
        alert("Enter student ID and course ID");
        return;
    }

    fetch(`/students/${studentId}/courses/${courseId}`, {
        method: "POST"
    })
        .then(res => res.text())
        .then(msg => alert(msg))
        .catch(err => console.error(err));
}

function loadStudentCourses() {
    const studentId = document.getElementById("viewStudentId").value;

    if (!studentId) {
        alert("Enter student ID");
        return;
    }

    fetch(`/students/${studentId}/courses`)
        .then(res => res.json())
        .then(renderStudentCourses)
        .catch(err => console.error(err));
}

function renderStudentCourses(courses) {
    const list = document.getElementById("studentCourses");
    list.innerHTML = "";

    if (!courses || courses.length === 0) {
        list.innerHTML = "<li>No courses for this student</li>";
        return;
    }

    courses.forEach(c => {
        list.innerHTML += `
          <li>
            <b>${c.title}</b> (id: ${c.id}) |
            credits: ${c.credits}
          </li>
        `;
    });
}

function render(students) {
    const list = document.getElementById("students");
    list.innerHTML = "";

    if (!students || students.length === 0) {
        list.innerHTML = "<li>No students</li>";
        return;
    }

    students.forEach(s => {
        list.innerHTML += `
          <li>
            <b>${s.name}</b> (id: ${s.id}) |
            age: ${s.age} |
            GPA: ${s.gpa}
            <button onclick="deleteStudent(${s.id})">❌</button>
          </li>
        `;
    });
}
function renderCourses(courses) {
    const list = document.getElementById("courses");
    list.innerHTML = "";

    if (!courses || courses.length === 0) {
        list.innerHTML = "<li>No courses</li>";
        return;
    }

    courses.forEach(c => {
        list.innerHTML += `
          <li>
            <b>${c.title}</b> (id: ${c.id}) |
            credits: ${c.credits}
            <button onclick="deleteCourse(${c.id})">❌</button>
          </li>
        `;
    });
}

loadAll();
loadCourses();

