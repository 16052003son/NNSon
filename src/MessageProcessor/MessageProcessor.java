package MessageProcessor;

class Queue {
    private int maxSize;
    private String[] queueArray;
    private int front, rear, size;

    public Queue(int size) {
        this.maxSize = size;
        this.queueArray = new String[maxSize];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(String item) {
        if (!isFull()) {
            rear = (rear + 1) % maxSize;
            queueArray[rear] = item;
            size++;
        } else {
            System.out.println("Queue day. Cannot add new elements.");
        }
    }

    public String dequeue() {
        if (!isEmpty()) {
            String temp = queueArray[front];
            front = (front + 1) % maxSize;
            size--;
            return temp;
        } else {
            return null;
        }
    }

    public boolean isFull() {
        return (size == maxSize);
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}

class Stack {
    private int maxSize;
    private String[] stackArray;
    private int top;

    public Stack(int size) {
        this.maxSize = size;
        this.stackArray = new String[maxSize];
        this.top = -1;
    }

    public void push(String item) {
        if (top < maxSize - 1) {
            stackArray[++top] = item;
        } else {
            System.out.println("Stack full. Cannot add new elements.");
        }
    }

    public String pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}

public class MessageProcessor {
    public static void main(String[] args) {
        Queue messageQueue = new Queue(5);
        Stack messageStack = new Stack(5);

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.print("Enter message (Enter 'exit' to exit): ");
            String message = scanner.nextLine();
            
            if (message.isEmpty()) {
                System.out.println("Error message cannot be blank, please enter again.");
                continue;
            }

            if (message.equalsIgnoreCase("Exit")) {
                break;
            }

            if (message.length() > 250) {
                System.out.println("Error, message is too long, please re-enter (maximum 250 characters).");
                continue;
            }

            if (!messageQueue.isFull()) {
                messageQueue.enqueue(message);
            } else {
                System.out.println("Queue The limit of 5 messages has been reached. Cannot add new.");
                break;
            }
        }

        while (!messageQueue.isEmpty()) {
            String message = messageQueue.dequeue();
            messageStack.push(message);
        }

        System.out.println("The messages received are:");
        while (!messageStack.isEmpty()) {
            System.out.println(messageStack.pop());
        }

        
    }
}
