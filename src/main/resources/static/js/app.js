// 创建Vue应用
const { createApp, ref, onMounted } = Vue;

const app = createApp({
    setup() {
        // 数据
        const tasks = ref([]);
        const newTask = ref({
            title: '',
            description: '',
            priority: 'MEDIUM',
            deadline: ''
        });
        const sortBy = ref('priority');
        
        // 生命周期钩子
        onMounted(() => {
            fetchTasks();
        });
        
        // 方法
        const fetchTasks = async () => {
            try {
                const response = await axios.get(`/api/tasks?sortBy=${sortBy.value}`);
                tasks.value = response.data;
            } catch (error) {
                console.error('获取任务列表失败:', error);
                alert('获取任务列表失败，请刷新页面重试');
            }
        };
        
        const addTask = async () => {
            try {
                // 处理日期格式
                const taskToAdd = { ...newTask.value };
                if (taskToAdd.deadline) {
                    taskToAdd.deadline = new Date(taskToAdd.deadline).toISOString();
                }
                
                const response = await axios.post('/api/tasks', taskToAdd);
                if (response.data.success) {
                    // 重置表单
                    newTask.value = {
                        title: '',
                        description: '',
                        priority: 'MEDIUM',
                        deadline: ''
                    };
                    // 刷新任务列表
                    fetchTasks();
                } else {
                    alert('添加任务失败');
                }
            } catch (error) {
                console.error('添加任务失败:', error);
                alert('添加任务失败，请重试');
            }
        };
        
        const completeTask = async (task) => {
            try {
                if (task.status === 'COMPLETED') return;
                
                const response = await axios.put(`/api/tasks/${task.id}/complete`);
                if (response.data.success) {
                    // 刷新任务列表
                    fetchTasks();
                } else {
                    alert('标记任务完成失败');
                }
            } catch (error) {
                console.error('标记任务完成失败:', error);
                alert('标记任务完成失败，请重试');
            }
        };
        
        const deleteTask = async (task) => {
            if (!confirm(`确定要删除任务「${task.title}」吗？`)) return;
            
            try {
                const response = await axios.delete(`/api/tasks/${task.id}`);
                if (response.data.success) {
                    // 刷新任务列表
                    fetchTasks();
                } else {
                    alert('删除任务失败');
                }
            } catch (error) {
                console.error('删除任务失败:', error);
                alert('删除任务失败，请重试');
            }
        };
        
        const getPriorityBadgeClass = (priority) => {
            switch (priority) {
                case 'HIGH': return 'priority-high';
                case 'MEDIUM': return 'priority-medium';
                case 'LOW': return 'priority-low';
                default: return 'bg-secondary';
            }
        };
        
        const formatDate = (dateString) => {
            const date = new Date(dateString);
            return date.toLocaleString('zh-CN', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit'
            });
        };
        
        return {
            tasks,
            newTask,
            sortBy,
            fetchTasks,
            addTask,
            completeTask,
            deleteTask,
            getPriorityBadgeClass,
            formatDate
        };
    }
});

app.mount('#app');