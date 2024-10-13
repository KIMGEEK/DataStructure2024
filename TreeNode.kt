typealias Visitor<T> = (TreeNode<T>) -> Unit // TreeNode를 받아 작업을 수행하고 아무 것도 반환하지 않는 것을 visitor라 지칭한다

typealias BinaryVisitor<T> = (T) -> Unit

class TreeNode<T> (val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf() // 빈 껍데기

    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>) { // println(it.value)
        visit(this) // this는 현재의 노드. 노드.value를 print
        children.forEach { // 현재 노드의 자식 노드마다 재귀호출
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = LinkedListQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }
        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

    fun search(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachLevelOrder {
            if (it.value == value) {
                result = it
            }
            // println("visited: ${it.value}")
        }
        return result
    }

}