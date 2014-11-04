% CONCATENAR DUAS LISTAS

concat([], L2, L2).
concat([A|L1], L2, LF) :- concat(L1, [A|L2], LF).

% CONTAR O TAMANHO DA LISTA

size([], 0).
size([_|L], P) :- size(L, Plinha), P is Plinha+1.

% TODAS AS PEÇAS AINDA NÃO PERTENCEM A NINGUÉM

listar_pecas(L) :- L =
        [peca(0,0),
	peca(0,1),
	peca(0,2),
	peca(0,3),
	peca(0,4),
	peca(0,5),
	peca(0,6),
	peca(1,1),
	peca(1,2),
	peca(1,3),
	peca(1,4),
	peca(1,5),
	peca(1,6),
	peca(2,2),
	peca(2,3),
	peca(2,4),
	peca(2,5),
	peca(2,6),
	peca(3,3),
	peca(3,4),
	peca(3,5),
	peca(3,6),
	peca(4,4),
	peca(4,5),
	peca(4,6),
	peca(5,5),
	peca(5,6),
	peca(6,6)].

iniciar_pecas(L) :- listar_pecas(L1), random_permutation(L1, L).

% VERIFICAR SE UMA PEÇA É ENCAIXÁVEL

encaixavel(peca(A, B), A, B, peca(A, B)).
encaixavel(peca(A, B), A, B, peca(B, A)) :- !.
encaixavel(peca(A, B), B, A, peca(B, A)).
encaixavel(peca(A, B), B, A, peca(A, B)) :- !.
encaixavel(peca(A, B), A, _, peca(A, B)) :- !.
encaixavel(peca(A, B), _, A, peca(A, B)) :- !.
encaixavel(peca(A, B), B, _, peca(B, A)) :- !.
encaixavel(peca(A, B), _, B, peca(B, A)) :- !.

% PREPARAR LISTA DE PEÇAS JOGÁVEIS

preparar([], _, _, []).
preparar([peca(A, B)|Pecas], Esq, Dir, [peca(C, D)|Possiveis]) :- 
    encaixavel(peca(A, B), Esq, Dir, peca(C, D)), !, preparar(Pecas, Esq, Dir, Possiveis).
preparar([_|Pecas], Esq, Dir, Possiveis) :- 
    preparar(Pecas, Esq, Dir, Possiveis).

/*
 *  INTELIGÊNCIA ARTIFICAL
 */

% AI BURRA: tudo no sort

dumb(Lista, Esq, Dir, _) :- preparar(Lista, Esq, Dir, L), L = [], !, fail.

dumb(Lista, Esq, Dir, peca(A, B)) :- preparar(Lista, Esq, Dir, ListaInicial),
	random_permutation(ListaInicial, [peca(C,D)|_]),
	preparar_peca(peca(C, D), peca(A, B)).

% AI ESPERTA

smart(Lista, _, Esq, Dir, _, _, _) :- preparar(Lista, Esq, Dir, L), L = [], !, fail.

smart(Lista, Jogadas, Esq, Dir, MelhorInimigo, Nini, peca(A, B)) :-
	size(Lista, P), P<MelhorInimigo, !,
	concat(Lista, Jogadas, Outras), listar_pecas(Todas),
	subtract(Todas, Outras, Inimigas),
	preparar(Lista, Esq, Dir, ListaInicial),
	preparar(Inimigas, Esq, Dir, PecasInimigas),
	combo(ListaInicial, [], PecasInimigas, Nini, ListaFinal),
	maior(ListaFinal, _, peca(A, B)).

smart(Lista, Jogadas, Esq, Dir, MelhorInimigo, Nini, peca(A, B)) :- 
	size(Lista, P), P == MelhorInimigo, maybe, !,
	concat(Lista, Jogadas, Outras), listar_pecas(Todas),
	subtract(Todas, Outras, Inimigas),
	preparar(Lista, Esq, Dir, ListaInicial),
	preparar(Inimigas, Esq, Dir, PecasInimigas),
	combo(ListaInicial, [], PecasInimigas, Nini, ListaFinal),
	maior(ListaFinal, _, peca(A, B)).

smart(Lista, Jogadas, Esq, Dir, _, _, peca(A, B)) :- 
	concat(Lista, Jogadas, Outras), listar_pecas(Todas),
	subtract(Todas, Outras, Inimigas),
	preparar(Lista, Esq, Dir, ListaInicial),
	preparar(Inimigas, Esq, Dir, PecasInimigas),
	evitar(ListaInicial, [], PecasInimigas, ListaFinal),
	maior(ListaFinal, _, peca(A, B)).

% COMBO - tenta colocar uma peça com a maior probabilide de um inimigo colocá-la e eu poder colocar de novo

combo([], _, _, _, []).

combo([peca(A, B)|L], LA, PecasInimigas, Nini, [[peca(A, B), P]|L2]) :- 
    combo(L, [peca(A, B)|LA], PecasInimigas, Nini, L2),
    concat(L, LA, LC),
    peca_combo(LC, peca(A, B), [], PecasInimigas, Nini, P).
    
peca_combo([], peca(_, _), _, _, 0, P) :- P is 0, !.

peca_combo([peca(B, _)|LC], peca(A, B), _, _, 0, P) :- !,
    peca_combo(LC, peca(A, B), _, _, 0, P1), P is P1+1.

peca_combo([peca(_, B)|LC], peca(A, B), _, _, 0, P) :- !,
    peca_combo(LC, peca(A, B), _, _, 0, P1), P is P1+1.

peca_combo([peca(_, _)|LC], peca(A, B), _, _, 0, P) :- !,
    peca_combo(LC, peca(A, B), _, _, 0, P).

peca_combo(_, peca(_, _), _, [], _, 0) :- !.

peca_combo(LC, peca(A, B), PIA, [peca(B, C)|PID], Nini, P) :- !,
    concat(PIA, PID, PIF), Nini2 is Nini-1,
    peca_combo(LC, peca(A, B), [peca(B, C)|PIA], PID, Nini, P2), 
    peca_combo(LC, peca(B, C), [], PIF, Nini2, P1), P is P1+P2.

peca_combo(LC, peca(A, B), PIA, [peca(C, B)|PID], Nini, P) :- !,
    concat(PIA, PID, PIF), Nini2 is Nini-1,
    peca_combo(LC, peca(A, B), [peca(C, B)|PIA], PID, Nini, P2),
    peca_combo(LC, peca(C, B), [], PIF, Nini2, P1), P is P1+P2.

peca_combo(LC, peca(A, B), PIA, [peca(C, D)|PID], Nini, P) :- !,
    peca_combo(LC, peca(A, B), [peca(C, D)|PIA], PID, Nini, P).

% EVITAR - evita que o inimigo consiga colocar sua peca

evitar([], _, _, []).
evitar([peca(A, B)|L], LD, ListaInimigos, [[peca(A, B), P]|ListaFinal]) :- 
    evitar(L, [peca(A, B)|LD], ListaInimigos, ListaFinal),
    concat(LD, L, LF), peso_evitar(peca(A, B), LF, ListaInimigos, P).

peso_evitar(peca(A, B), MinhasPecas, [], D) :- 
    contar_decimos(peca(A, B), MinhasPecas, D).
peso_evitar(peca(A, B), MinhasPecas, [peca(B, _)|ListaInimigos], P) :- 
    peso_evitar(peca(A, B), MinhasPecas, ListaInimigos, P1),
    !, P is P1-1.
peso_evitar(peca(A, B), MinhasPecas, [peca(_, B)|ListaInimigos], P) :- 
    peso_evitar(peca(A, B), MinhasPecas, ListaInimigos, P1),
    !, P is P1-1.
peso_evitar(peca(A, B), MinhasPecas, [_|MP], P) :- 
    peso_evitar(peca(A, B), MinhasPecas, MP, P).

contar_decimos(peca(_, _), [], 0).
contar_decimos(peca(A, B), [peca(B, _)|MP], D) :- !, contar_decimos(peca(A, B), MP, D1), D is D1+(1/10).
contar_decimos(peca(A, B), [peca(_, B)|MP], D) :- !, contar_decimos(peca(A, B), MP, D1), D is D1+(1/10).
contar_decimos(peca(A, B), [_|MP], D) :- contar_decimos(peca(A, B), MP, D).

% Maior valor de uma lista dupla de valores

maior([peca(A, B), P], P, peca(A, B)) :- !.
maior([[peca(_, _), P]|L], M1, peca(C, D)) :- maior(L, M1, peca(C, D)), M1>P, !.
maior([[peca(_, _), P]|L], M1, peca(C, D)) :- maior(L, M1, peca(C, D)), M1==P, maybe, !.
maior([[peca(A, B), P]|_], P, peca(A, B)).

% PREPARAR PEÇA PARA JOGAR DE VOLTA AO JAVA

preparar_peca(peca(A, B), peca(A, B)) :- A =< B, !.
preparar_peca(peca(A, B), peca(B, A)).


% point AI

pointAI(Lista, Esq, Dir, peca(A, B)) :- 
    preparar(Lista, Esq, Dir, ListaInicial),
    pontuacao_lista(ListaInicial, ListaPontuada),existeDupla(ListaPontuada, P, peca(A,B)),!.

pointAI(Lista, Esq, Dir, peca(A, B)) :- 
    preparar(Lista, Esq, Dir, ListaInicial),
    pontuacao_lista(ListaInicial, ListaPontuada),maior(ListaPontuada, _, peca(A, B)).


pontuacao(peca(A,B), C) :- (A>B), !, C is A-B.
pontuacao(peca(A,B), C) :- C is B-A.

pontuacao_lista([], []).
pontuacao_lista([peca(A, B)|L], [[peca(A, B), P]|LP]) :- pontuacao_lista(L, LP), pontuacao(peca(A, B), P).

existeDupla([peca(A, A), P], P, peca(A, A)) :- !.
existeDupla([[peca(A, B), P]|L], M1, peca(C, D)) :- existeDupla([peca(A, B), P], M1, peca(C, D)),!.
existeDupla([[peca(_, _), P]|L], M1, peca(C, D)) :- existeDupla(L, M1, peca(C, D)),!.
%existeDupla([[peca(A, B), P]|_], P, peca(A, B)).
