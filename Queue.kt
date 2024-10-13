interface QueueInterface<T> {
    fun enqueue(element: T): Boolean
    fun dequeue(): T?
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0 // getter까지 구현
    fun peek(): T?
}

class ArrayListQueue<T>: QueueInterface<T> { // 큐인터페이스 상속
    private val list = arrayListOf<T>() // 빈 arrayList

    override val count: Int
        get() = list.size
    override fun peek(): T? = list.getOrNull(0) // 맨 위를 빼지 않고 엿보기만
    // { return list.getOrNull(0) }과 동일함
    // .getOrNull(0)은 0번째 아이템(맨앞) get해서 리턴. 아이템 하나도 없으면 null 리턴.

    override fun enqueue(element: T): Boolean {
        list.add(element)
        return true
    }
    override fun dequeue(): T? = // 삭제하면서 리턴까지(변수에 넣어 사용하기 위해)
        if (isEmpty) null else list.removeAt(0) //

    override fun toString(): String = list.toString()
}

class LinkedListQueue<T> : QueueInterface<T> {
    private val list = LinkedList<T>()
    private var size = 0
    override val count: Int
        get() = size

    override fun peek(): T? = list.nodeAt(0)?.value
    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++
        return true
    }
    override fun dequeue(): T? {
        val firstNode = list.nodeAt(0) ?: return null
        size--
        return list.removeHead()
    }
    override fun toString(): String = list.toString()
}

class RingBufferQueue<T> (size: Int) : QueueInterface<T> {
    private val ringBuffer: RingBuffer<T> = RingBuffer(size)
    override val count: Int
        get() = ringBuffer.count
    override fun peek(): T? = ringBuffer.first()

    override fun enqueue(element: T): Boolean =
        ringBuffer.write(element)
    override fun dequeue(): T? =
        if (isEmpty) null else ringBuffer.read()
    override fun toString(): String = ringBuffer.toString()
}