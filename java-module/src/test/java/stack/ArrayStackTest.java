package stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ArrayStackTest {

    private ArrayStack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<>(3);
    }

    @Test
    @DisplayName("새로운 스택은 비어있어야 함")
    void newStackShouldBeEmpty() {
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isZero();
    }

    @Test
    @DisplayName("push 연산은 요소를 스택에 추가해야 함")
    void pushShouldAddElementToStack() {
        stack.push("item1");

        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.size()).isEqualTo(1);
        assertThat(stack.peek()).isEqualTo("item1");
    }

    @Test
    @DisplayName("peek 연산은 요소를 제거하지 않고 상단 요소를 반환해야 함")
    void peekShouldReturnTopElementWithoutRemovingIt() {
        stack.push("item1");
        stack.push("item2");

        assertThat(stack.peek()).isEqualTo("item2");
        assertThat(stack.size()).isEqualTo(2);
        assertThat(stack.peek()).isEqualTo("item2");
    }

    @Test
    @DisplayName("pop 연산은 상단 요소를 제거해야 함")
    void popShouldRemoveTopElement() {
        stack.push("item1");
        stack.push("item2");

        stack.pop();

        assertThat(stack.size()).isEqualTo(1);
        assertThat(stack.peek()).isEqualTo("item1");
    }

    @Test
    @DisplayName("빈 스택에서 peek 연산은 예외를 발생시켜야 함")
    void peekOnEmptyStackThrowsException() {
        assertThatThrownBy(() -> stack.peek())
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("빈 스택에서 pop 연산은 예외를 발생시켜야 함")
    void popOnEmptyStackDecreasesTopCounter() {
        assertThatThrownBy(() -> stack.pop())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("스택이 가득 찼을 때 resize 동작 확인")
    void stackShouldResizeWhenFull() {
        stack.push("item1");
        stack.push("item2");
        stack.push("item3");

        stack.push("item4");

        assertThat(stack.size()).isEqualTo(4);
        assertThat(stack.peek()).isEqualTo("item4");

        stack.push("item5");
        stack.push("item6");

        assertThat(stack.size()).isEqualTo(6);
        assertThat(stack.peek()).isEqualTo("item6");
    }

    @Test
    @DisplayName("LIFO(후입선출) 순서가 유지되는지 확인")
    void lifoOrderShouldBeMaintained() {
        String[] items = {"item1", "item2", "item3", "item4", "item5"};

        for (String item : items) {
            stack.push(item);
        }

        for (int i = items.length - 1; i >= 0; i--) {
            assertThat(stack.peek()).isEqualTo(items[i]);
            stack.pop();
        }

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("여러 번의 push와 pop 연산이 올바르게 동작해야 함")
    void multiplePushAndPopOperationsShouldWorkCorrectly() {
        stack.push("item1");
        stack.push("item2");

        stack.pop();
        assertThat(stack.peek()).isEqualTo("item1");

        stack.push("item3");
        stack.push("item4");

        assertThat(stack.size()).isEqualTo(3);
        assertThat(stack.peek()).isEqualTo("item4");

        stack.pop(); // remove item4
        stack.pop(); // remove item3
        stack.pop(); // remove item1

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("다른 타입(Integer)의 스택도 올바르게 동작해야 함")
    void stackWithIntegerElementsShouldWorkCorrectly() {
        ArrayStack<Integer> intStack = new ArrayStack<>(5);

        intStack.push(10);
        intStack.push(20);
        intStack.push(30);

        assertThat(intStack.peek()).isEqualTo(30);
        intStack.pop();
        assertThat(intStack.peek()).isEqualTo(20);
        assertThat(intStack.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("경계값: 정확히 가득 찬 스택에서 resize")
    void resizeWithExactlyFullStack() {
        stack.push("item1");
        stack.push("item2");
        stack.push("item3");

        assertThat(stack.size()).isEqualTo(3);

        stack.push("item4");

        assertThat(stack.size()).isEqualTo(4);
        assertThat(stack.peek()).isEqualTo("item4");
    }
}
