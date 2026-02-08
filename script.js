let students = [];

const form = document.getElementById('studentForm');
const studentList = document.getElementById('studentList');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    
    const id = document.getElementById('studentId').value;
    const name = document.getElementById('studentName').value;
    
    students.push({ id: parseInt(id), name: name });
    
    form.reset();
    displayStudents();
});

function displayStudents() {
    if (students.length === 0) {
        studentList.innerHTML = '<div class="empty-message">No students added yet</div>';
        return;
    }
    
    studentList.innerHTML = students.map(student => 
        `<div class="student-item">
            <span>${student.id}</span> - ${student.name}
        </div>`
    ).join('');
}

displayStudents();
