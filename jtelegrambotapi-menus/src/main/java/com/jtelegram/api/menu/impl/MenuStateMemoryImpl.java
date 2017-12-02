package com.jtelegram.api.menu.impl;

import com.jtelegram.api.menu.MenuState;
import com.jtelegram.api.menu.MenuStateMemory;
import com.jtelegram.api.menu.exception.StateMemoryDepletedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.annotation.Nonnull;

public class MenuStateMemoryImpl implements MenuStateMemory {

    private final MenuImpl menu;
    private final Stack<MenuStateImpl> stateStack;
    private int capacity;

    MenuStateMemoryImpl(@Nonnull MenuImpl menu, @Nonnull MenuStateImpl initialState) {
        this.menu = menu;
        this.capacity = MenuStateMemory.DEFAULT_CAPACITY;
        this.stateStack = new Stack<>();
        this.stateStack.ensureCapacity(capacity);
        this.stateStack.push(initialState);
    }

    private void fixStackSizes() {
        int cap = this.capacity;
        for (int i = this.stateStack.size(); i > cap; i++) {
            this.stateStack.remove(i - 1);
        }
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (stateStack.size() > capacity) {
            stateStack.removeAll(stateStack.subList(capacity, stateStack.size()));
        }
        this.stateStack.ensureCapacity(capacity);
        this.capacity = capacity;
    }

    @Override
    public int getSize() {
        return stateStack.size();
    }

    @Override
    public void pushState(@Nonnull MenuState menuState) {
        stateStack.push((MenuStateImpl) menuState);
        fixStackSizes();
    }

    @Nonnull
    @Override
    public MenuStateImpl peekState(int stepsBack) {
        if (stepsBack < 1) {
            throw new IllegalArgumentException("stepsBack must be >= 0");
        }
        int size = stateStack.size();
        if (stepsBack >= size) {
            throw new IllegalArgumentException("stepsBack must be < size");
        }
        return stateStack.elementAt(size - stepsBack);
    }

    @Nonnull
    @Override
    public MenuStateImpl peekState() {
        return stateStack.peek();
    }

    private List<MenuStateImpl> popState0(int count) throws StateMemoryDepletedException {
        if (stateStack.size() <= count) {
            throw new StateMemoryDepletedException();
        }
        List<MenuStateImpl> menuStateList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            MenuStateImpl state = stateStack.pop();
            menuStateList.add(state);
        }
        fixStackSizes();
        return menuStateList;
    }

    @Nonnull
    @Override
    public MenuStateImpl popState() throws StateMemoryDepletedException {
        return popState0(1).get(0);
    }

    @Nonnull
    @Override
    public List<MenuStateImpl> popState(int count) throws StateMemoryDepletedException {
        return popState0(count);
    }

}
