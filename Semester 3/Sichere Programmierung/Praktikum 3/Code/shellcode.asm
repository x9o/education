bits 64

section .text
global _start

_start:
xor rcx, rcx
push rcx
mov rcx, 0x68732f6e69622fff
shr rcx, 8
push rcx
push rsp
pop rdi

xor rcx, rcx
push rcx
push word 0x632d
push rsp
pop rbx

xor rcx, rcx
push rcx
jmp command

execve:
pop rdx
push rdx
xor byte [rdx+5], 0x41
push rbx
push rdi
push rsp
pop rsi

xor rdx,rdx
xor rax,rax
mov al, 59

syscall

command:
call execve
data: db "ls -lA"
