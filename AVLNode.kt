import java.lang.Math.max

class AVLNode<T: Comparable<T>>(var value: T) {
    var height = 0
    var leftChild: AVLNode<T>? = null
    var rightChild: AVLNode<T>? = null
    val min: AVLNode<T>?
        get() = leftChild?.min ?: this

    val leftHeight: Int
        get() = leftChild?.height ?: -1
    val rightHeight: Int
        get() = rightChild?.height ?: -1
    val balanceFactor: Int
        get() = leftHeight - rightHeight

    fun height(node: AVLNode<T>? = this): Int {
        return node?.let {
            1 + max(height(node.leftChild), height(node.rightChild)) // height는 높이 1 추가
        } ?: -1 // 그림이 아닌 코드로 이해해야 한다
    }
    private fun diagram(node: AVLNode<T>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChild, "$top ", "$top┌──", "$top│ ") +
                        root + "${node.value}\n" +
                        diagram(node.leftChild, "$bottom│ ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }
    override fun toString() = diagram(this)
}