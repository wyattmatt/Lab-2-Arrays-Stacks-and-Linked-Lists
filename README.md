# Daily Task Manager

## 📌 Introduction
Daily Task Manager is a Java-based command-line application that helps users manage their daily tasks efficiently. This program demonstrates the use of **arrays, stacks, and linked lists** to handle task management operations dynamically.

## 🛠 Features
### ✅ Task Management
- **View predefined tasks** stored in an array.
- **Update a predefined task** in the array.
- **Add new tasks** dynamically using a linked list.
- **Remove tasks** from the dynamic list.
- **View the dynamic task list**.

### 🔄 Task Completion & Undo
- **Mark a task as completed** (moves task from the dynamic list to the undo stack).
- **View completed tasks** stored in the stack.
- **Undo last completed task** by pressing the `z` key (restores task to the dynamic list).

### 🖥 User-Friendly CLI
- **Interactive menu for easy navigation.**
- **Error handling** for invalid inputs.
- **Clear console after every action** for better readability.

## 📂 Folder Structure
This project follows a structured workspace with the following folders:
- `src/` - Contains the source code files.
- `lib/` - Stores external dependencies.
- `bin/` - Stores compiled output files (generated automatically).

> To customize the folder structure, edit `.vscode/settings.json` and update the relevant settings.

## 🚀 How to Run
1. **Compile the program**
   ```sh
   javac DailyTaskManager.java
   ```
2. **Run the program**
   ```sh
   java DailyTaskManager
   ```
3. **Follow the on-screen menu** to manage tasks.

## 🎮 Usage Example
```
===== Daily Task Manager =====
1. View tasks
2. Update a task
3. Mark task as completed
4. Add new task
5. Remove task
6. View dynamic task list
7. View completed tasks
8. Exit
Enter choice: 
```

- **Press `z` to undo the last completed task.**
- **Tasks removed from completed list will be restored to the dynamic list.**

## 🏆 Why This Project?
This project was created as an **Object-Oriented Programming (OOP) assignment** to demonstrate:
- **Arrays** (for storing predefined tasks)
- **Stacks** (for task completion & undo feature)
- **Linked Lists** (for managing tasks dynamically)
- **User interaction & error handling** in Java

## 📜 License
This project is for educational purposes. Feel free to modify and improve it!