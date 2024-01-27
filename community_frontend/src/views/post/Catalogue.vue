<template>
	<el-card class="box-card" shadow="hover">
		<div slot="header" class="title is-6  el-icon-notebook-2">
          目录
        </div>
		<div>
		<el-tree class="tree" ref="tree" node-key="uuid" :data="treeData" :props="defaultProps" default-expand-all>
			<div class="custom-tree-node" slot-scope="{ node, data }">
				<div @click="toDiv(data.uuid)">{{ data.text }}</div>
				<!-- <a class="anchor" :href="`#${data.uuid}`">
					{{ data.text }}
				</a> -->
			</div>
		</el-tree>
		</div>
		<!-- <div v-html="content" class="markdown-body html-content"></div> -->
	</el-card>
</template>

<script>
	import "github-markdown-css"
	import hljs from 'highlight.js'
	import 'highlight.js/styles/atom-one-light.css' //引入一种语法的高亮
	import {
		marked
	} from 'marked'
	export default {
		data() {
			return {
				treeData: [],
				defaultProps: {
					label: 'text',
					children: 'children',
				}
			}
		},
		// created() {
		// 	this.$nextTick(() => {
		// 		this.getCatalog()
		// 		hljs.highlightAll()
		// 	})
		// },
		// computed: {
		// 	content() {
		// 		return marked(post_content)
		// 	}
		// },
		mounted() {
			this.getCatalog()
			// this.getCommentByTopicId(this.$route.params.id)
		},
		methods: {
			// 根据内容获取目录
			getCatalog() {
				const h = ['h1', 'h2', 'h3', 'h4', 'h5', 'h6']
				var elements = $('#header')
				console.log('elements', elements)
				let hElements = []
				let key
				let i
				for (i = 0; i < elements.length; i++) {
					key = elements[i]
					if (h.indexOf(key.localName) > -1) {
						let text
						if (key.children && key.children.length) {
							text = this.getText(key.children)
						} else {
							text = key.innerHTML
						}
						hElements.push({
							hLevel: parseInt(key.localName[1]),
							text,
							id: key.localName,
							uuid: key.id,
						})
					}
				}
				console.log('hElements:', hElements)
				
				let result = this.toTree(hElements)
				this.treeData = result

				// 目录默认选中第一个
				this.$nextTick(() => {
					if (!result) return
					this.$refs.tree.setCurrentKey(result[0].uuid)
				})
				console.log('result:', result)
			},
			toTree(flatArr) {
				var tree = []
				var copyArr = flatArr.map(function(item) {
					return item
				})

				// 根据指定级别查找该级别的子孙级，并删除掉已经查找到的子孙级
				var getChildrenByLevel = function(currentLevelItem, arr, level) {
					if (!currentLevelItem) {
						return
					}
					// 将level值转成负数，再进行比较
					var minusCurrentLevel = -currentLevelItem.hLevel
					var children = []
					for (var i = 0, len = arr.length; i < len; i++) {
						var levelItem = arr[i]
						if (-levelItem.hLevel < minusCurrentLevel) {
							children.push(levelItem)
						} else {
							// 只找最近那些子孙级
							break
						}
					}
					// 从数组中删除已经找到的那些子孙级，以免影响到其他子孙级的查找
					if (children.length > 0) {
						arr.splice(0, children.length)
					}
					return children
				}

				var getTree = function(result, arr, level) {
					// 首先将数组第一位移除掉，并添加到结果集中
					var currentItem = arr.shift() || {}

					currentItem.hLevel = level
					result.push(currentItem)
					while (arr.length > 0) {
						if (!currentItem) {
							return
						}
						// 根据当前级别获取它的子孙级
						var children = getChildrenByLevel(currentItem, arr, level)
						// 如果当前级别没有子孙级则开始下一个
						if (children.length == 0) {
							currentItem = arr.shift()
							currentItem.hLevel = level
							if (currentItem) {
								result.push(currentItem)
							}
							continue
						}
						currentItem.children = []
						// 查找到的子孙级继续查找子孙级
						getTree(currentItem.children, children, level + 1)
					}
				}
				getTree(tree, copyArr, 1)
				return tree
			},
			getText() {
				let result = null
				if (!arr.length) return
				for (let i = 0; i < arr.length; i++) {
					if (arr[i].children && arr[i].children.length) {
						result = this.getText(arr[i].children)
					} else {
						result = arr[i].innerHTML
					}
				}

				return result
			},
			toDiv(uuid) {
				document.getElementById(uuid).scrollIntoView({
					behavior: 'smooth',
				})
			},
		}
	}
</script>

<style>
	.content {
		display: flex;
		/* padding: 30px; */
		overflow: hidden;
		height: 100vh;
	}

	.tree {
		margin-right: 15px;
		overflow-y: auto;
		width: 280px;
		flex-shrink: 0;
	}

	.html-content {
		overflow-y: auto;
	}
</style>
