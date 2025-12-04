import { useState } from "react";

interface UserFormProps {
    userId?: string; // se presente, il form fa update
}

const UserForm = ({ userId }: UserFormProps) => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [displayName, setDisplayName] = useState("");
    const [roles, setRoles] = useState<string[]>([]); // ["USER", "ADMIN"]

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const payload = { username, email, displayName, roles };
        const url = userId ? `/api/users/${userId}` : "/api/users";
        const method = userId ? "PUT" : "POST";

        try {
            const res = await fetch(url, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });

            if (!res.ok) throw new Error("Errore nella richiesta");

            alert(userId ? "Utente aggiornato!" : "Utente creato!");
        } catch (err) {
            console.error(err);
            alert("Si è verificato un errore");
        }
    };

    const toggleRole = (role: string) => {
        setRoles(prev => prev.includes(role) ? prev.filter(r => r !== role) : [...prev, role]);
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Username:</label>
                <input value={username} onChange={e => setUsername(e.target.value)} required />
            </div>
            <div>
                <label>Email:</label>
                <input type="email" value={email} onChange={e => setEmail(e.target.value)} required />
            </div>
            <div>
                <label>Display Name:</label>
                <input value={displayName} onChange={e => setDisplayName(e.target.value)} />
            </div>
            <div>
                <label>Roles:</label>
                <label>
                    <input type="checkbox" checked={roles.includes("USER")} onChange={() => toggleRole("USER")} /> USER
                </label>
                <label>
                    <input type="checkbox" checked={roles.includes("ADMIN")} onChange={() => toggleRole("ADMIN")} /> ADMIN
                </label>
            </div>
            <button type="submit">{userId ? "Aggiorna" : "Crea"} utente</button>
        </form>
    );
};

export default UserForm;
