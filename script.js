const form = document.getElementById('studentForm');
const studentList = document.getElementById('studentList');

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('studentId').value;
    const name = document.getElementById('studentName').value;
    
    await fetch('/api/students', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: parseInt(id), name: name })
    });
    
    form.reset();
    loadStudents();
});

async function loadStudents() {
    const response = await fetch('/api/students');
    const students = await response.json();
    
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

loadStudents();
