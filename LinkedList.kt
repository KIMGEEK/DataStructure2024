class LinkedList<T> : Iterable<T>, Collection<T> { // Collection 상속(Iterable은 Collection에 포함됨)
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0 // collection이 size변수 포함하는 interface여서
        private set // read only로 만듦

    override fun iterator(): Iterator<T> {
        return LinkedListIterator(this)
    }
    override fun isEmpty(): Boolean { // collection이 isEmpty()를 포함하는 interface여서
        return size == 0 //size값이 0인지 체크
    }

    override fun contains(element: T): Boolean {
        for (item in this)
            if (item == element) return true
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements)
            if (!contains(searched)) return false
        return true
    }

    override fun toString(): String {
        if(isEmpty()) {
            return "Empty list"
        }
        return head.toString()
    }

    fun push(value: T): LinkedList<T> {
        head = Node(value = value, next = head)
        if(tail == null) {
            tail = head//tail부터 하나씩 시작
        }
        size++

        return this;
    }

    fun append(value: T): LinkedList<T> {
        // 1
        if (isEmpty()) {
            push(value)
            return this
        }
        // 2
        tail?.next = Node(value = value)
        // 3
        tail = tail?.next
        size++

        return this
    }

    fun nodeAt(index: Int): Node<T>? {
        // 1
        var currentNode = head
        var currentIndex = 0
        // 2
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        // 1
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        // 2
        val newNode = Node(value = value, next = afterNode.next)
        // 3
        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T? {
        if(!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
        return result
    }

    fun removeLast(): T? {
        // 1
        val head = head ?: return null
        // 2
        if (head.next == null) return pop()
        // 3
        size--
        // 4
        var prev = head
        var current = head
        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
        // 5
        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value
        if (node.next == tail) {
            tail = node
        }
        if (node.next != null) {
            size--
        }
        node.next = node.next?.next
        return result
    }

    fun removeHead(): T? {
        val head = head ?: return null
        size--
        this.head = head.next
        if (isEmpty())
            this.tail = null
        return head.value
    }
}

class LinkedListIterator<K> (
    private val list: LinkedList<K>
) : Iterator<K> {
    private var index = 0
    private var lastNode: Node<K>?= null
    override fun next(): K {
        if (index >= list.size) throw IndexOutOfBoundsException()
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else
            lastNode?.next
        index++
        return lastNode!!.value
    }
    override fun hasNext(): Boolean {
        return index < list.size
    }
}