class Stack<Element>() : StackInterface<Element> {
    private val storage = arrayListOf<Element>()
    override fun toString() = buildString {
        appendLine("----top----")
        storage.asReversed().forEach {
            appendLine("$it")
        }
        appendLine("-----------")
    }

    override fun peek(): Element? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

    override fun push(element: Element) {
        storage.add(element)
    }

    override fun pop(): Element? {
        if (isEmpty) {
            return null
        }
        return storage.removeAt(count - 1)
    }
}