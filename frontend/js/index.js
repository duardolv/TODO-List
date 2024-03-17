let tasks = [];
let editIndex = null;

document.getElementById('taskForm').addEventListener('submit', function(event) {
    event.preventDefault();

    let task = {
        name: document.getElementById('taskName').value,
        description: document.getElementById('taskDescription').value,
        date: document.getElementById('taskDate').value,
        priority: document.getElementById('taskPriority').value,
        category: document.getElementById('taskCategory').value,
        status: document.getElementById('taskStatus').value,
    };

    if (editIndex !== null) {
        tasks[editIndex] = task;
        editIndex = null;
    } else {
        tasks.push(task);
    }

    listTasks();
});

function listTasks() {
    let taskList = document.querySelector('.to-do');
    taskList.innerHTML = '';

    tasks.forEach(function(task, index) {
        let taskElement = document.createElement('div');
        taskElement.className = 'task';
        taskElement.innerHTML = `
            <h2 class="task-title">${task.name}</h2>
            <p class="task-description">${task.description}</p>
            <p class="task-date">${task.date}</p>
            <p class="task-priority">Prioridade: ${task.priority}</p>
            <p class="task-category">Categoria: ${task.category}</p>
            <p class="task-status">Status: ${task.status}</p>
            <button class="task-button" onclick="deleteTask(${index})">Excluir</button>
            <button class="task-button" onclick="editTask(${index})">Editar</button>
        `;

        taskList.appendChild(taskElement);
    });
}

function deleteTask(index) {
    tasks.splice(index, 1);
    listTasks();
}

function editTask(index) {
    let task = tasks[index];
    document.getElementById('taskName').value = task.name;
    document.getElementById('taskDescription').value = task.description;
    document.getElementById('taskDate').value = task.date;
    document.getElementById('taskPriority').value = task.priority;
    document.getElementById('taskCategory').value = task.category;
    document.getElementById('taskStatus').value = task.status;

    editIndex = index;
}
