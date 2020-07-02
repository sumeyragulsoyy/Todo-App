package com.example.TODO.service;

import com.example.TODO.dao.TodoItemDao;
import com.example.TODO.model.Status;
import com.example.TODO.model.TodoItem;
import com.example.TODO.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TodoItemService implements TodoItemDao {


    @Autowired
    private TodoItemRepository itemRepository;

    @Override
    public void createTodoItem(TodoItem item)
    {
        itemRepository.save(item);
        updateParentDependency(item);
    }

    public void updateParentDependency(TodoItem item){
        long parentId=item.getDependentTo();
        TodoItem parent=getTodoItem(parentId);
        parent.setDependentTo(item.getId());
        itemRepository.save(parent);
    }

    public TodoItem getTodoItem(long id) {
       return  itemRepository.findById(id);
    }

    public void deleteTodoFromList(long id){
        itemRepository.deleteById((id));
    }

    public void markComplete(long ItemId){ // to be deleted,check this item id in the dependento column at the todoitems table
        TodoItem toBeCompleted = getTodoItem(ItemId);
        Optional<TodoItem> dependentItemToWillBeDeletedItem=itemRepository.findByDependentTo(ItemId);

        if(!dependentItemToWillBeDeletedItem.isEmpty()  ){ // there is 1 subitem
            if(toBeCompleted.getStatus()==Status.PROGRESS && dependentItemToWillBeDeletedItem.get().getStatus() ==Status.COMPLETED){
                toBeCompleted.setStatus(Status.COMPLETED);
                itemRepository.save(toBeCompleted);
            }
            } else if (toBeCompleted.getStatus()==Status.PROGRESS) {
            toBeCompleted.setStatus(Status.COMPLETED);
            itemRepository.save(toBeCompleted);
        }

    }
}
