interface StackInterface<Element> {
    val count: Int
        get

    fun peek(): Element?
    val isEmpty: Boolean
        get() = count == 0
    fun push(element: Element)
    fun pop(): Element?
}